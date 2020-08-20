package com.app.daggerauth.di

import androidx.lifecycle.ViewModelProvider
import com.app.daggerauth.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    // providing the instance of viewModelProviderFactory
    @Binds
    abstract fun bindViewModelFactory (modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}