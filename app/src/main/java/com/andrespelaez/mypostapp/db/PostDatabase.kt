package com.andrespelaez.mypostapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrespelaez.mypostapp.data.Post

@Database(
    entities = [
        Post::class],
    version = 1,
    exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

}
