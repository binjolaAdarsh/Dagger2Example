package com.app.daggerauth.ui.main.profile

import androidx.lifecycle.ViewModel
import com.app.daggerauth.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager):ViewModel() {
    companion object{
        const val TAG= "ProfileViewModel"
    }

    fun getAuthUser() = sessionManager.cachedUser
}