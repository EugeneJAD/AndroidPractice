package com.eugene.androidpractice.ui.animation.shared

import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eugene.androidpractice.R
import com.eugene.androidpractice.di.Injectable
import com.eugene.androidpractice.ui.base.AppNavigator
import kotlinx.android.synthetic.main.fragment_shared_element_first.*
import javax.inject.Inject

class SharedElementFirstFragment : Fragment(), Injectable {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shared_element_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changeTransform = TransitionInflater.from(context).inflateTransition(R.transition.image_shared_element_transition)
        changeTransform.duration = 600
        user_icon.setOnClickListener {
            appNavigator.navigateToSharedElementFragmentTwo(user_icon, changeTransform)
        }
    }
}
