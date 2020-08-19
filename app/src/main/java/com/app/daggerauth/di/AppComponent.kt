package com.app.daggerauth.di

import android.app.Application
import com.app.daggerauth.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class,
                        ActivityBuilderModule::class
])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder{
        // if we want to bind particular instance of obj to component at the time of construction
        @BindsInstance
        fun application(app: Application):Builder

        fun build():AppComponent
    }
}