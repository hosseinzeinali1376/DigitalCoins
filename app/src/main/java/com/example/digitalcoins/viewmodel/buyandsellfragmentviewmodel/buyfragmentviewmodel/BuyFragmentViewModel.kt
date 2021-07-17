package com.example.digitalcoins.viewmodel.buyandsellfragmentviewmodel.buyfragmentviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalcoins.model.items.Item
import com.example.digitalcoins.model.items.coinsitems.User
import com.example.digitalcoins.model.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class BuyFragmentViewModel(val userRepo: UserRepository) : ViewModel() {

    private val TAG: String = "HosseinZeinaliNesaz"
    private val buyInformation: MutableLiveData<User> = MutableLiveData()

    fun getBuyInformation(): LiveData<User> {
        return buyInformation
    }

    fun readBuyInformation() = viewModelScope.launch {
        val call = userRepo.readUserInfo()
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                val getUsers = response.body()
                getUsers?.let {
                    buyInformation.value = it
                } ?: run {
                    buyInformation.value = null
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("=====>>ERROR", t.message.toString())
            }
        })
    }

}