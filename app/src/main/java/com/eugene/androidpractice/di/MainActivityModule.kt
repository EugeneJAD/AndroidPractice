package com.eugene.androidpractice.di

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun provideActivity(activity: MainActivity): FragmentActivity = activity
}