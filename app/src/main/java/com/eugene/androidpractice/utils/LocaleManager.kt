package com.eugene.androidpractice.utils

import android.content.res.Configuration
import android.content.res.Resources
import com.eugene.androidpractice.data.repository.local.PrefsManager
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * For API level < 24 (Nougat)
 * Build.VERSION.SDK_INT < Build.VERSION_CODES.N
 */
@Singleton
class LocaleManager @Inject constructor(private val prefsManager: PrefsManager,
                                        val resources: Resources) {

    fun setLocale(){
        setNewLocale(getLanguage())
    }

    private fun setNewLocale(language: String){
        updateResources(language)
    }

    private fun getLanguage(): String = prefsManager.getLanguageCode()

    @Suppress("DEPRECATION")
    private fun updateResources(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

}