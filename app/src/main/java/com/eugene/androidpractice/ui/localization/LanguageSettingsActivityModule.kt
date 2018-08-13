package com.eugene.androidpractice.ui.localization

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class LanguageSettingsActivityModule {
    @Provides
    internal fun provideActivity(activity: LanguageSettingsActivity): FragmentActivity = activity
}