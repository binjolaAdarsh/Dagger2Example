package com.app.daggerauth.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.app.daggerauth.R
import com.app.daggerauth.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions.placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions) =
            Glide.with(application).setDefaultRequestOptions(requestOptions)

        @Singleton
        @Provides
        fun provideLogo(application: Application): Drawable? {
            return ContextCompat.getDrawable(application, R.drawable.logo)!!
        }

    }
}