package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.animation.shared.SecondSharedAnimationsActivity
import dagger.Module
import dagger.Provides

@Module
class SecondAnimationsActivityModule {
    @Provides
    internal fun provideActivity(activity: SecondSharedAnimationsActivity): FragmentActivity = activity
}