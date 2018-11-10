package com.eugene.androidpractice.ui.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.base.AppNavigator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main_animations.*
import javax.inject.Inject

class MainAnimationsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animations)
    }

    fun clickHandler(v: View){
        when(v.id){
            key_frame_button.id -> appNavigator.navigateToKeyFrameAnimations()
            shared_element_button.id -> appNavigator.navigateToSharedElementAnimations()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
