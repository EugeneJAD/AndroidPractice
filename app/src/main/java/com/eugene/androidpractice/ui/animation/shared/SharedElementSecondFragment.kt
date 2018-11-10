package com.eugene.androidpractice.ui.animation.shared

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eugene.androidpractice.R
import com.eugene.androidpractice.di.Injectable

class SharedElementSecondFragment : Fragment(), Injectable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shared_element_second, container, false)
    }
}
