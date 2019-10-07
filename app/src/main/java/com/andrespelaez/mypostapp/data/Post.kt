package com.andrespelaez.mypostapp.data

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Post(
    var JSONPlaceholder: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = "",
    var read: Boolean? = false,
    var fav: Boolean? = false
)

enum class PostType {
    ALL,
    FAVORITES
}