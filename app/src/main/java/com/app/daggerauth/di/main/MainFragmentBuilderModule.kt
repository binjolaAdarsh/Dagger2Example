package com.app.daggerauth.di.main

import com.app.daggerauth.ui.main.post.PostFragment
import com.app.daggerauth.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}