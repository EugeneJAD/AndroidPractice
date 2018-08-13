package com.eugene.androidpractice.ui.localization

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.eugene.androidpractice.R
import com.eugene.androidpractice.data.repository.local.PrefsManager
import com.eugene.androidpractice.utils.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_language_settings.*
import java.util.*
import javax.inject.Inject

class LanguageSettingsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var prefsManager: PrefsManager

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
        setContentView(R.layout.activity_language_settings)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            localeManager.setLocale()

        button_english.setOnClickListener {
            if(prefsManager.getLanguageCode() != LANGUAGE_CODE_ENGLISH) {
                prefsManager.saveLanguageCode(LANGUAGE_CODE_ENGLISH)
                showToast("App language is English")
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

        button_ukrainian.setOnClickListener {
            if(prefsManager.getLanguageCode() != LANGUAGE_CODE_UKRAINIAN) {
                prefsManager.saveLanguageCode(LANGUAGE_CODE_UKRAINIAN)
                showToast("Мова додатка українська")
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
