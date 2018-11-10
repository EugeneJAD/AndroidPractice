package com.eugene.androidpractice.di

import com.eugene.androidpractice.ui.animation.shared.SharedElementFirstFragment
import com.eugene.androidpractice.ui.animation.shared.SharedElementSecondFragment
import com.eugene.androidpractice.ui.rx.RxPracticeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule{

    @ContributesAndroidInjector
    internal abstract fun contributeRxPracticeFragment(): RxPracticeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSharedElementFirstFragment(): SharedElementFirstFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSharedElementSecondFragment(): SharedElementSecondFragment
}