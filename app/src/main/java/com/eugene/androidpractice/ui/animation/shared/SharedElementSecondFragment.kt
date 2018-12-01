package com.eugene.androidpractice.ui.animation.shared

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eugene.androidpractice.R
import com.eugene.androidpractice.di.Injectable
import com.eugene.androidpractice.ui.base.AppNavigator
import kotlinx.android.synthetic.main.fragment_shared_element_second.*
import javax.inject.Inject

class SharedElementSecondFragment : Fragment(), Injectable {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shared_element_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user_icon2.setOnClickListener { appNavigator.navigateToSecondSharedAnimationsActivity(user_icon2, title2) }
    }
}
