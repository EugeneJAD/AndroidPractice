package com.eugene.androidpractice.ui.base
import com.eugene.androidpractice.R
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
        navigation.replaceFragment(R.id.fragment_container, RxPracticeFragment(),null )
    }

    fun navigateToLanguageSettings() {
        navigation.startActivityForResult(LanguageSettingsActivity::class.java, SWITCH_LANGUAGE_REQUEST)
    }
}
