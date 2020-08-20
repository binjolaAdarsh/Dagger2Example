package com.app.daggerauth.di.auth

import androidx.lifecycle.ViewModel
import com.app.daggerauth.di.ViewModelKey
import com.app.daggerauth.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel):ViewModel
}