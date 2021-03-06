package com.eugene.androidpractice.ui.animation.shared

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.transition.AutoTransition
import android.transition.Fade
import android.view.Window
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.base.AppNavigator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainSharedAnimationsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()
        setContentView(R.layout.activity_main_shared_animations)
        val moveTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        if (savedInstanceState == null) {
            appNavigator.navigateToSharedElementFragmentOne(moveTransition)
        }
    }

    private fun setupWindow() {
        val moveTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        val changeTransform = TransitionInflater.from(this).inflateTransition(R.transition.image_shared_element_transition)
        changeTransform.duration = 600
        // enable transitions (if you did not enable transitions in your theme)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Fade()
            exitTransition = Fade()
            sharedElementEnterTransition = AutoTransition()
            sharedElementExitTransition = AutoTransition()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) is SharedElementFirstFragment)
            finish()
        else
            super.onBackPressed()
    }
}
