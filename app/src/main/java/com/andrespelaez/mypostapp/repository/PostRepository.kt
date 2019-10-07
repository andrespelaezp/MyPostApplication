package com.andrespelaez.mypostapp.repository

import androidx.lifecycle.LiveData
import com.andrespelaez.mypostapp.AppExecutors
import com.andrespelaez.mypostapp.api.ApiResponse
import com.andrespelaez.mypostapp.api.PostService
import com.andrespelaez.mypostapp.data.Post
import com.andrespelaez.mypostapp.db.PostDao
import com.andrespelaez.mypostapp.vo.Resource
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val postDao: PostDao,
    private val postService: PostService
) {

    fun loadPosts(type: Boolean): LiveData<Resource<MutableList<Post>>> {
        return object : NetworkBound<MutableList<Post>, MutableList<Post>>(appExecutors) {

            override fun saveCallResult(item: MutableList<Post>) {
                item.map {
                    postDao.insert(it)
                }
            }

            override fun shouldFetch(data: MutableList<Post>?): Boolean {
                if (data == null)
                    return true
                return false
            }

            override fun loadFromDb(): LiveData<MutableList<Post>> {
                return postDao.getPostbyType(type)
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<Post>>> {
                return postService.getPosts()
            }

            }
        .asLiveData()

    }
}
