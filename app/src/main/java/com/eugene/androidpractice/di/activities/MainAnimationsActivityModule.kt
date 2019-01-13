package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.animation.MainAnimationsActivity
import dagger.Module
import dagger.Provides

@Module
class MainAnimationsActivityModule {
    @Provides
    internal fun provideActivity(activity: MainAnimationsActivity): FragmentActivity = activity
}