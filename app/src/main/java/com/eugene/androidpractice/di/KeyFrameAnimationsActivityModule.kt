package com.eugene.androidpractice.di

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.animation.KeyFrameAnimationsActivity
import dagger.Module
import dagger.Provides

@Module
class KeyFrameAnimationsActivityModule {
    @Provides
    internal fun provideActivity(activity: KeyFrameAnimationsActivity): FragmentActivity = activity
}