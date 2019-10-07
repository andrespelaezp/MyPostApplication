package com.andrespelaez.mypostapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andrespelaez.mypostapp.data.Post
import com.andrespelaez.mypostapp.data.PostType
import com.andrespelaez.mypostapp.repository.PostRepository
import com.andrespelaez.mypostapp.vo.Resource
import javax.inject.Inject

class PostViewModel
@Inject constructor(
    postRepository: PostRepository
) : ViewModel() {

    private val _posts = MutableLiveData<MutableList<Post>>()
    val post: LiveData<MutableList<Post>> get() = _posts

    private val _type = MutableLiveData<Boolean>()

    val posts: LiveData<Resource<MutableList<Post>>> = Transformations.switchMap(_posts) { type ->
        if (type == null) {
            AbsentLiveData.create()
        } else {
            postRepository.loadPosts(_type.value?: false)
        }
    }

    fun setType(type: Boolean?) {
        _type.value = type?: false
    }
}

/**
 * A LiveData class that has `null` value.
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
