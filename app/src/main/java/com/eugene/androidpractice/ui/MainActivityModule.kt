package com.eugene.androidpractice.ui

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun provideActivity(activity: MainActivity): FragmentActivity = activity
}