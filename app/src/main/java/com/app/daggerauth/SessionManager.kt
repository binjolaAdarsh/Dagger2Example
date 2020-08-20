package com.app.daggerauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.app.daggerauth.models.User
import com.app.daggerauth.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    companion object {
        const val TAG = "SessionManager"
    }

    private val _cachedUser = MediatorLiveData<AuthResource<User>>()
    val cachedUser: LiveData<AuthResource<User>> = _cachedUser
    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        _cachedUser.value = AuthResource.loading()
        _cachedUser.addSource(source) {
            _cachedUser.value = it
            _cachedUser.removeSource(source)
        }
    }

    fun logout() {
        _cachedUser.value = AuthResource.logout()
    }

}