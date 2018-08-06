package com.eugene.androidpractice.di

import com.eugene.androidpractice.ui.rx.RxPracticeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule{

    @ContributesAndroidInjector
    internal abstract fun contributeRxPracticeFragment(): RxPracticeFragment
}