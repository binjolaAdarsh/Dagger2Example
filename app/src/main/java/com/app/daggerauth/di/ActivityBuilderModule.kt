package com.app.daggerauth.di

import com.app.daggerauth.di.auth.AuthModule
import com.app.daggerauth.di.auth.AuthViewModelModule
import com.app.daggerauth.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// these class should be abstract
@Module
abstract class ActivityBuilderModule {

    // let dagger know that auth activity is potential client
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
}