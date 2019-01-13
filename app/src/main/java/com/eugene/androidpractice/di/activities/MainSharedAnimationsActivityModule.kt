package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.animation.shared.MainSharedAnimationsActivity
import dagger.Module
import dagger.Provides

@Module
class MainSharedAnimationsActivityModule {
    @Provides
    internal fun provideActivity(activity: MainSharedAnimationsActivity): FragmentActivity = activity
}