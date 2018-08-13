package com.eugene.androidpractice.ui.rx

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eugene.androidpractice.R
import com.eugene.androidpractice.di.Injectable
import kotlinx.android.synthetic.main.fragment_rx_practice.*
import javax.inject.Inject

class RxPracticeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RxPracticeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rx_practice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RxPracticeFragmentViewModel::class.java)
        if(savedInstanceState == null)
            viewModel.getSampleObservable()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.result.observe(this, Observer {
            result_text.text = it
        })
    }
}
