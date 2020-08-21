package com.app.daggerauth.di.main

import com.app.daggerauth.network.main.MainApi
import com.app.daggerauth.ui.main.post.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    companion object{

        @Provides
        fun provideMainApi(retrofit: Retrofit): MainApi {
            return  retrofit.create(MainApi::class.java)
        }

        @Provides
        fun provideAdapter(): PostRecyclerAdapter {
            return  PostRecyclerAdapter()
        }
    }

}