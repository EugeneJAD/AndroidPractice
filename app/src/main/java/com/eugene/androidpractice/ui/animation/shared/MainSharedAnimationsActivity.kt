package com.eugene.androidpractice.ui.animation.shared

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
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
        setContentView(R.layout.activity_main_shared_animations)
        val changeTransform = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        if (savedInstanceState == null) {
            appNavigator.navigateToSharedElementFragmentOne(changeTransform)
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
