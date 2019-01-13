package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.rx.RXActivity
import dagger.Module
import dagger.Provides

@Module
class RxActivityModule {
    @Provides
    internal fun provideActivity(activity: RXActivity): FragmentActivity = activity
}