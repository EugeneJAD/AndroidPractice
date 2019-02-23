package com.eugene.androidpractice.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.eugene.androidpractice.R
import com.eugene.androidpractice.ui.base.AppNavigator
import com.eugene.androidpractice.utils.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var appNavigator: AppNavigator

    @Inject
    lateinit var localeManager: LocaleManager

    override fun attachBaseContext(newBase: Context?) {
        var base = newBase
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val locale = Locale(newBase?.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
                    ?.getString(PREFS_LANGUAGE_KEY, LANGUAGE_CODE_ENGLISH)?.toLowerCase())
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            base = newBase!!.createConfigurationContext(config)
        }
        super.attachBaseContext(base)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            localeManager.setLocale()
    }

    fun clickHandler(v: View){
        when(v.id){
            rx_button.id -> appNavigator.navigateToRx()
            localization_button.id -> appNavigator.navigateToLanguageSettings()
            animations_button.id -> appNavigator.navigateToAnimations()
            inflater_button.id -> appNavigator.navigateToInflater()
            coroutines_button.id -> appNavigator.navigateToCoroutines()
            media_button.id -> appNavigator.navigateToMedia()
            rv_button.id -> appNavigator.navigateToRV()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SWITCH_LANGUAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            //Recreate activity to create new ConfigurationContext with appropriate resources
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                localeManager.setLocale()
            recreate()
        }
    }
}
