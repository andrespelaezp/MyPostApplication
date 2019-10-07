package com.andrespelaez.mypostapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andrespelaez.mypostapp.data.Post
import com.andrespelaez.mypostapp.data.PostType

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(post: Post)

    @Update
    fun updatePost(post: Post)

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPostbyId(id: Int): LiveData<MutableList<Post>>

    @Query("SELECT * FROM post WHERE fav = :type")
    fun getPostbyType(type: Boolean): LiveData<MutableList<Post>>

    @Query("SELECT * FROM post")
    fun getPosts(): LiveData<MutableList<Post>>
}
