package com.eugene.androidpractice.ui.animation.shared

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.transition.Explode
import android.transition.Fade
import android.view.Window
import com.eugene.androidpractice.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SecondSharedAnimationsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()
        setContentView(R.layout.activity_second_shared_animations)
    }

    private fun setupWindow() {
        val moveTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        val changeTransform = TransitionInflater.from(this).inflateTransition(R.transition.image_shared_element_transition)
        changeTransform.duration = 600
        with(window) {
            // enable transitions (if you did not enable transitions in your theme)
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Fade()
            exitTransition = Fade()
            sharedElementEnterTransition = moveTransition
            sharedElementExitTransition = changeTransform
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
