package com.eugene.androidpractice.data.repository.local

import android.content.Context
import android.content.SharedPreferences
import com.eugene.androidpractice.utils.PREFS_LANGUAGE_KEY
import com.eugene.androidpractice.utils.SP_NAME
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsManager @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun deleteSP() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveString(key: String, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveLong(key: String, value: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun retrieveString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue)
    }

    fun retrieveInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun retrieveLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    fun retrieveBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun retrieveFloat(key: String, defValue: Float): Float {
        return sharedPreferences.getFloat(key, defValue)
    }

    fun saveLanguageCode(languageCode: String) {
        saveString(PREFS_LANGUAGE_KEY, languageCode)
    }

    fun getLanguageCode(): String =
        retrieveString(PREFS_LANGUAGE_KEY, Locale.getDefault().language).toLowerCase()
}