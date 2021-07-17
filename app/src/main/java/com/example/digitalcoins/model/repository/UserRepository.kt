package com.example.digitalcoins.model.repository

import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.model.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepository @Inject constructor() {
    @Inject
    lateinit var retrofit: Retrofit

    fun readUserInfo(): Call<User> {
        val jsonPlaceHolderApi = retrofit.create(RetrofitService::class.java)
        val myCall: Call<User> = jsonPlaceHolderApi.getMovieList()
        return myCall
    }

    // use flow
    /*fun readUserInfo(): kotlinx.coroutines.flow.Flow<MutableList<Item>> =
        flow {
            val jsonPlaceHolderApi = retrofit.create(RetrofitService::class.java)
            val myCall: MutableList<Item> = jsonPlaceHolderApi.getMovieList()
            emit(myCall)
        }.flowOn(Dispatchers.IO)*/
}