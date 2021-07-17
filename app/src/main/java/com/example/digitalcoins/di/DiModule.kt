package com.example.digitalcoins.di

import android.content.Context
import androidx.room.Room
import com.example.digitalcoins.model.retrofit.Common
import com.example.digitalcoins.model.roomdb.MyAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DiModule {

    lateinit var database: MyAppDatabase

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Common.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*@Singleton
    @Provides
    fun provideInterface(retrofit: Retrofit): RetrofitServiceApi {
        return retrofit.create(RetrofitServiceApi::class.java)
    }*/

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MyAppDatabase {
        return Room.databaseBuilder(
            appContext,
            MyAppDatabase::class.java, "_HosseinZeinaliNesaz"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    /*@Provides
    fun provideObjectDao(myDao: MyDao): MyDao {
        return database.myDao()
    }*/
}