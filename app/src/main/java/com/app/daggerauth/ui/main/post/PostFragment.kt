package com.app.daggerauth.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggerauth.R
import com.app.daggerauth.ui.main.Status
import com.app.daggerauth.utils.VerticalSpaceItemDecoration
import com.app.daggerauth.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostFragment : DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var postAdapter: PostRecyclerAdapter
    lateinit var viewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory)[PostViewModel::class.java]
        initRecyclerView()
        observe()
    }

    fun observe() {
        viewModel.observePost()?.removeObservers(viewLifecycleOwner)
        viewModel.observePost()?.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        postAdapter.setPosts(it.data!!)
                    }
                    Status.ERROR ->  { }
                    Status.LOADING -> { }
                }
            }
        })
    }

    fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

            addItemDecoration(VerticalSpaceItemDecoration(15))
            this.adapter = postAdapter
        }
    }

    companion object {
        fun getInstance(): PostFragment {
            return PostFragment()
        }
    }
}