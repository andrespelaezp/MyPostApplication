package com.andrespelaez.mypostapp.di

import android.app.Application
import androidx.room.Room
import com.andrespelaez.mypostapp.api.PostService
import com.andrespelaez.mypostapp.db.PostDao
import com.andrespelaez.mypostapp.db.PostDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providePostService(): PostService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): PostDatabase {
        return Room
            .databaseBuilder(app, PostDatabase::class.java, "post.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostDatabase): PostDao {
        return db.postDao()
    }

}
