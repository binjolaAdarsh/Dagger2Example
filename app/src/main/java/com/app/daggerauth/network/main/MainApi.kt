package com.app.daggerauth.network.main

import com.app.daggerauth.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
     //posts?userId=1
    @GET("posts")
    fun getPost(@Query("userId")id:Int): Flowable<List<Post>>
}