package com.app.daggerauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.app.daggerauth.models.User
import com.app.daggerauth.ui.auth.AuthActivity
import com.app.daggerauth.ui.auth.AuthResource
import com.app.daggerauth.ui.auth.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.cachedUser.observe(this, Observer<AuthResource<User>> {
            it?.let {
                when (it.status) {
                    AuthStatus.LOADING -> {
//                        showProgressbar(true)
                    }
                    AuthStatus.ERROR -> {
//                        showProgressbar(false)
                    }
                    AuthStatus.AUTHENTICATED -> {
//                        showProgressbar(false)
                        Log.d("TAG", "attemptLogin: ${it.data.toString()}")

                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
//                        showProgressbar(false)
                        navLoginScreen()
                    }
                }
            }
        })

    }

    fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}