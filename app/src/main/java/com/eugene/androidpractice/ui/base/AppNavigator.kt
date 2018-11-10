package com.eugene.androidpractice.ui.base

import android.media.Image
import android.support.annotation.IdRes
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionInflater
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.ImageView
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.animation.KeyFrameAnimationsActivity
import com.eugene.androidpractice.ui.animation.MainAnimationsActivity
import com.eugene.androidpractice.ui.animation.shared.MainSharedAnimationsActivity
import com.eugene.androidpractice.ui.animation.shared.SharedElementFirstFragment
import com.eugene.androidpractice.ui.animation.shared.SharedElementSecondFragment
import com.eugene.androidpractice.ui.localization.LanguageSettingsActivity
import com.eugene.androidpractice.ui.rx.RXActivity
import com.eugene.androidpractice.ui.rx.RxPracticeFragment
import com.eugene.androidpractice.utils.SWITCH_LANGUAGE_REQUEST
import javax.inject.Inject

class AppNavigator @Inject constructor(private val navigation: NavigationController) {

    fun navigateToRx() {
        navigation.startActivity(RXActivity::class.java)
    }

    fun navigateToRxPracticeFragment() {
        navigation.replaceFragment(R.id.fragment_container, RxPracticeFragment(), null)
    }

    fun navigateToLanguageSettings() {
        navigation.startActivityForResult(LanguageSettingsActivity::class.java, SWITCH_LANGUAGE_REQUEST)
    }

    fun navigateToAnimations() {
        navigation.startActivity(MainAnimationsActivity::class.java)
    }

    fun navigateToKeyFrameAnimations() {
        navigation.startActivity(KeyFrameAnimationsActivity::class.java)
    }

    fun navigateToSharedElementAnimations() {
        navigation.startActivity(MainSharedAnimationsActivity::class.java)
    }

    fun navigateToSharedElementFragmentOne(elementReturnTransition: Transition) {
        navigation.activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                        SharedElementFirstFragment().apply {
                            sharedElementReturnTransition = elementReturnTransition
                            enterTransition = Fade().apply { duration = 300 }
                            exitTransition = Fade().apply { duration = 300 }
                        })
                .addToBackStack(null)
                .commit()
    }

    fun navigateToSharedElementFragmentTwo(sharedImage: AppCompatImageView,
                                           elementEnterTransition: Transition) {
        val fm = navigation.activity.supportFragmentManager
        fm.beginTransaction()
                .addSharedElement(sharedImage, sharedImage.transitionName)
                .replace(R.id.fragment_container,
                        SharedElementSecondFragment().apply {
                            // 1. Shared Elements Transition
                            sharedElementEnterTransition = elementEnterTransition
                            // 2. Enter/exit Transition for new Fragment
                            enterTransition = Fade().apply { duration = 300 }
                            exitTransition = Fade().apply { duration = 300 }
                        })
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }
}
