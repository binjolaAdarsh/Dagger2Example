package com.app.daggerauth.ui.main.post

import android.util.Log
import androidx.lifecycle.*
import com.app.daggerauth.SessionManager
import com.app.daggerauth.models.Post
import com.app.daggerauth.network.main.MainApi
import com.app.daggerauth.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    var _posts: MediatorLiveData<Resource<List<Post>>>? = null

    fun observePost(): LiveData<Resource<List<Post>>>? {
        if (_posts == null) {
            _posts = MediatorLiveData<Resource<List<Post>>>()

            _posts?.value = Resource.loading()
            val source :LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPost(sessionManager.cachedUser.value?.data?.id!!)
                    .onErrorReturn {
                        Log.e("TAG", "observePost: $it")
                        var post = Post(-1, -1, "", "")
                        var arrayList = ArrayList<Post>()
                        arrayList.add(post)
                        arrayList
                    }.map {
                        if (it.isNotEmpty()) {
                            if (it[0].id == -1)
                                return@map Resource.error<List<Post>>("Something went wrong")

                        }
                        return@map Resource.success<List<Post>>(it)
                    }.subscribeOn(Schedulers.io())
            )
            _posts?.addSource(source,Observer<Resource<List<Post>>>{
                _posts?.value = it
                _posts?.removeSource(source)
            })
        }
return _posts

    }
}