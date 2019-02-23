package com.eugene.androidpractice.di.activities

import android.support.v4.app.FragmentActivity
import com.eugene.androidpractice.ui.recyclerview.RecyclerViewActivity
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewActivityModule {
    @Provides
    internal fun provideActivity(activity: RecyclerViewActivity): FragmentActivity = activity
}