package com.eugene.androidpractice.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.eugene.androidpractice.ui.rx.RxPracticeFragmentViewModel
import com.eugene.androidpractice.viewmodel.PracticeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: PracticeViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RxPracticeFragmentViewModel::class)
    abstract fun bindRxPracticeFragmentViewModel(viewModel: RxPracticeFragmentViewModel): ViewModel
}

