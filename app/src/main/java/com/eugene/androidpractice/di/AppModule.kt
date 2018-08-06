package com.eugene.androidpractice.di

import android.app.Application
import android.content.res.Resources
import com.eugene.androidpractice.data.repository.DataRepository
import com.eugene.androidpractice.data.repository.Repository
import com.eugene.androidpractice.data.repository.local.PrefsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideResources(application: Application): Resources  = application.resources

    @Provides
    @Singleton
    fun providePrefsManager(application: Application): PrefsManager = PrefsManager(application)

    @Provides
    @Singleton
    fun provideRepository(dataRepository: DataRepository): Repository = dataRepository
}
