package com.andrespelaez.mypostapp.api

import androidx.lifecycle.LiveData
import com.andrespelaez.mypostapp.data.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("/posts")
    fun getPosts(): LiveData<ApiResponse<MutableList<Post>>>

}