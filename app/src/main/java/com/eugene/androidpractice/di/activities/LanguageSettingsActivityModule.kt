package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.localization.LanguageSettingsActivity
import dagger.Module
import dagger.Provides

@Module
class LanguageSettingsActivityModule {
    @Provides
    internal fun provideActivity(activity: LanguageSettingsActivity): FragmentActivity = activity
}