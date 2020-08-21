package com.app.daggerauth.di

import com.app.daggerauth.di.auth.AuthModule
import com.app.daggerauth.di.auth.AuthScope
import com.app.daggerauth.di.auth.AuthViewModelModule
import com.app.daggerauth.di.main.MainFragmentBuilderModule
import com.app.daggerauth.di.main.MainModule
import com.app.daggerauth.di.main.MainScope
import com.app.daggerauth.di.main.MainViewModelModule
import com.app.daggerauth.ui.auth.AuthActivity
import com.app.daggerauth.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// these class should be abstract
@Module
abstract class ActivityBuilderModule {

    // let dagger know that auth activity is potential client
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
@MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class, MainViewModelModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}