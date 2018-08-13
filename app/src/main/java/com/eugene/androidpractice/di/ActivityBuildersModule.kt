package com.eugene.androidpractice.di

import com.eugene.androidpractice.ui.MainActivity
import com.eugene.androidpractice.ui.MainActivityModule
import com.eugene.androidpractice.ui.localization.LanguageSettingsActivity
import com.eugene.androidpractice.ui.localization.LanguageSettingsActivityModule
import com.eugene.androidpractice.ui.rx.RXActivity
import com.eugene.androidpractice.ui.rx.RxActivityModule
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
}

