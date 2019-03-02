package com.eugene.androidpractice.ui.coroutines

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_coroutines.*
import javax.inject.Inject

class CoroutinesActivity : BaseActivity<CoroutinesViewModel>(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        if (savedInstanceState == null) {
            viewModel.startSimpleCoroutine()
            viewModel.loadTitles()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.snackbar.observe(this, Observer {
            it?.let { message -> Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show() }
        })
        viewModel.loading.observe(this, Observer {
            it?.let { loading -> progress.visibility = if (loading) View.VISIBLE else View.GONE }
        })
        viewModel.listOfItems.observe(this, Observer {list ->
            list?.let { result.text = it.map { it.title }.toString()}
        })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
