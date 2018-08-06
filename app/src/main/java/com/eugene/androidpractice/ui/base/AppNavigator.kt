package com.eugene.androidpractice.ui.base
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.rx.RXActivity
import com.eugene.androidpractice.ui.rx.RxPracticeFragment
import javax.inject.Inject

class AppNavigator @Inject constructor(private val navigation: NavigationController) {

    fun navigateToRx() {
        navigation.startActivity(RXActivity::class.java)
    }

    fun navigateToRxPracticeFragment() {
        navigation.replaceFragment(R.id.fragment_container, RxPracticeFragment(),null )
    }
}
