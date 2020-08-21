package com.app.daggerauth.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.daggerauth.R
import com.app.daggerauth.models.User
import com.app.daggerauth.ui.auth.AuthResource
import com.app.daggerauth.ui.auth.AuthStatus
import com.app.daggerauth.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView: profileFragment")
        return inflater.inflate(R.layout.fragment_profile,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, providerFactory)[ProfileViewModel::class.java]
    }

    fun subscribeObserver() {
        viewModel.getAuthUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthUser().observe(viewLifecycleOwner, Observer<AuthResource<User>> {
            it?.let {
                when (it.status) {
                    AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthStatus.ERROR -> {
                        setErrorDetails(it.msg)
                    }
                }
            }
        })
    }

    private fun setErrorDetails(msg: String?) {
        msg?.let {
            email.text = it
            username.text = "error"
            website.text = "error"

        }

    }

    private fun setUserDetails(data: User?) {
        data?.let {
            email.text = it.email
            username.text = it.username
            website.text = it.website
        }

    }

    companion object {
        fun getInstance():ProfileFragment{
            return ProfileFragment()
        }
    }
}
