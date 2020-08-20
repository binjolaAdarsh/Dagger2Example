package com.app.daggerauth.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.app.daggerauth.models.User
import com.app.daggerauth.network.auth.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

private    var _authUser = MediatorLiveData<AuthResource<User>>()
    val authUser: LiveData<AuthResource<User>> = _authUser

    fun authenticateWithId(userId:Int){
        _authUser.value= AuthResource.loading()
        val source : LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)
            .onErrorReturn {
                var errorUser=User(-1)
                errorUser
            }.map {
                if(it.id==-1){
                    AuthResource.error("could not authenticate")
                }else{
                     AuthResource.authenticated(it)
                }
            }
            .subscribeOn(Schedulers.io()))

        _authUser.addSource(source){
            _authUser.value=it
            _authUser.removeSource(source)
        }
    }


}