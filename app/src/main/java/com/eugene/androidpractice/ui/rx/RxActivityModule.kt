package com.eugene.androidpractice.ui.rx

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class RxActivityModule {
    @Provides
    internal fun provideActivity(activity: RXActivity): FragmentActivity = activity
}