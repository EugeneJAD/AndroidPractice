package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.coroutines.CoroutinesActivity
import dagger.Module
import dagger.Provides

@Module
class CoroutinesActivityModule {
    @Provides
    internal fun provideActivity(activity: CoroutinesActivity): FragmentActivity = activity
}