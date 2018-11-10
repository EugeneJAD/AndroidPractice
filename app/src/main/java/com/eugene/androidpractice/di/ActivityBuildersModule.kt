package com.eugene.androidpractice.di

import com.eugene.androidpractice.ui.MainActivity
import com.eugene.androidpractice.ui.animation.KeyFrameAnimationsActivity
import com.eugene.androidpractice.ui.animation.MainAnimationsActivity
import com.eugene.androidpractice.ui.animation.shared.MainSharedAnimationsActivity
import com.eugene.androidpractice.ui.localization.LanguageSettingsActivity
import com.eugene.androidpractice.ui.rx.RXActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [RxActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeRXActivity(): RXActivity

    @ContributesAndroidInjector(modules = [LanguageSettingsActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeLanguageSettingsActivity(): LanguageSettingsActivity

    @ContributesAndroidInjector(modules = [MainAnimationsActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeMainAnimationsActivity(): MainAnimationsActivity

    @ContributesAndroidInjector(modules = [KeyFrameAnimationsActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeKeyFrameAnimationsActivity(): KeyFrameAnimationsActivity

    @ContributesAndroidInjector(modules = [MainSharedAnimationsActivityModule::class, FragmentBuildersModule::class])
    abstract fun contributeMainSharedAnimationsActivity(): MainSharedAnimationsActivity
}

