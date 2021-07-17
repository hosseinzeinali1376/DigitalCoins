package com.example.digitalcoins.viewmodel.portfoviewmodel

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

class PortfoViewModel(val userRepo: UserRepository) : ViewModel() {
    private val TAG: String = "HosseinZeinaliNesaz"
    private val portofoMutableLiveData: MutableLiveData<User> = MutableLiveData()

    fun getProtfoLiveData(): LiveData<User> {
        return portofoMutableLiveData
    }

    fun setPortfoMutableLiveData() = viewModelScope.launch {
        val call = userRepo.readUserInfo()
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                val getData = response.body()
                getData?.let {
                    portofoMutableLiveData.value = it
                } ?: run {
                    //portofoMutableLiveData.value = ArrayList()
                    portofoMutableLiveData.value = null
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        })
    }
}