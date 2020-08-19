package com.app.daggerauth.di

import com.app.daggerauth.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// these class should be abstract
@Module
abstract class ActivityBuilderModule {

    // let dagger know that auth activity is potential client
    @ContributesAndroidInjector
    abstract  fun contributeAuthActivity():AuthActivity
}