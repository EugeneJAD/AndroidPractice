package com.eugene.androidpractice.ui.rx

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import com.eugene.androidpractice.R
import com.eugene.androidpractice.data.repository.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RxPracticeFragmentViewModel @Inject constructor(private val repository: Repository,
                                                      resources: Resources)
    : ViewModel() {

    private val _compositeDisposables = CompositeDisposable()
    val result = MutableLiveData<String>()
    var resultText: String = resources.getString(R.string.result)

    /**
     * Don't do this
     */
    fun getSampleObservable() {
        repository.getSampleObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        Timber.d("onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Timber.d("onSubscribe")
                        _compositeDisposables.add(d)
                    }

                    override fun onNext(t: String) {
                        Timber.d("onNext $t")
                        resultText += " $t"
                        result.value = resultText
                    }

                    override fun onError(e: Throwable) {
                        Timber.d("onError")
                    }
                })
    }

    override fun onCleared() {
        _compositeDisposables.clear()
        super.onCleared()
    }
}