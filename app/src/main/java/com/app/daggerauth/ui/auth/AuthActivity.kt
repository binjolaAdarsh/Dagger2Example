package com.app.daggerauth.ui.auth

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.daggerauth.R
import com.app.daggerauth.models.User
import com.app.daggerauth.viewmodel.ViewModelProviderFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    var logo: Drawable? = null
        @Inject set

    @Inject
    lateinit var  providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager:RequestManager

    lateinit var  viewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel =ViewModelProvider(this,providerFactory)[AuthViewModel::class.java]
        setLogo()

        login_button.setOnClickListener{
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        if(user_id_input.text.toString().isEmpty())
            return

        viewModel.authenticateWithId(user_id_input.text.toString().toInt())

        viewModel.authUser.observe(this,
            Observer<AuthResource<User>> {
                it?.let {
                    when(it.status){
                        AuthStatus.LOADING -> {
                            showProgressbar(true)
                        }
                        AuthStatus.ERROR -> {showProgressbar(false)}
                        AuthStatus.AUTHENTICATED -> {
                            showProgressbar(false)
                            Log.d("TAG", "attemptLogin: ${it.data.toString()}")

                        }
                        AuthStatus.NOT_AUTHENTICATED ->{
                            showProgressbar(false)
                        }
                    }
                }
            })
    }
private fun showProgressbar(flag:Boolean){
    progress_bar.visibility = if(flag) View.VISIBLE else View.GONE
}
    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}