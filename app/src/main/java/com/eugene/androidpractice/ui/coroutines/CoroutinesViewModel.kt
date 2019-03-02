package com.eugene.androidpractice.ui.coroutines

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import com.eugene.androidpractice.R
import com.eugene.androidpractice.data.model.SimpleModel
import com.eugene.androidpractice.utils.SingleLiveEvent
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

class CoroutinesViewModel @Inject constructor(val resources: Resources) : ViewModel() {

    /**
     * Request a snackbar to display a string.
     */
    private val _snackBar = SingleLiveEvent<String>()
    val snackbar: SingleLiveEvent<String>
        get() = _snackBar

    /**
     * Show a loading spinner if true
     */
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Dispatchers.Main is confined to the Main thread operating with UI objects.
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val listOfItems = MutableLiveData<List<SimpleModel>>()
    /**
     * Called from the CoroutinesActivity
     */
    fun startSimpleCoroutine() {
        launchDataLoad {
            loadSomething()
        }
    }

    /**
     * Called from the CoroutinesActivity.
     * Parallel calls with async and awaitAll.
     */
    fun loadTitles() {
        val list = mutableListOf<SimpleModel>().apply {
            for (i in 0..10)
                add(SimpleModel(i, ""))
        }
        listOfItems.value = list
        launchDataLoad {
            val listOfDeferred = list.map {
                GlobalScope.async {
                    it.title = loadTitle()
                    it
                }
            }
            listOfItems.value = listOfDeferred.awaitAll()
        }
    }

    @Throws(IOException::class)
    private suspend fun loadTitle(): String {
        delay(3000)
        val id = Random.nextInt(100)
        if (id > 95)
            throw IOException("Unknown Error Occurred")
        return "Item $id"
    }

    private suspend fun loadSomething() {
        delay(2000)
        _snackBar.value = resources.getString(R.string.hello_from_couroutines)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Helper function to call a data load function with a loading spinner, errors will trigger a
     * snackbar.
     *
     * By marking `block` as `suspend` this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the uiScope. Before calling the
     *              lambda the loading spinner will display, after completion or error the loading
     *              spinner will stop
     */
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return uiScope.launch {
            try {
                _loading.value = true
                block()
            } catch (error: IOException) {
                _snackBar.value = error.message
            } finally {
                _loading.value = false
            }
        }
    }
}
