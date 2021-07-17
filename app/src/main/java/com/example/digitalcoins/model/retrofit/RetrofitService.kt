package com.example.digitalcoins.model.retrofit

import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    //https://www.simplifiedcoding.net/demos/marvel
    @GET("data/pricemultifull?fsyms=BTC,ETH,LTC,USDT,XRP,BCH,BNB,EOS,XLM,ETC,TRX,DOGE,BUSD,PMN&tsyms=USD")
    // getMovieList(): Call<MutableList<User>>
    fun getMovieList(): Call<User>

    // use flow
    /*@GET("marvel")
    suspend fun getMovieList(): MutableList<Item>*/
}