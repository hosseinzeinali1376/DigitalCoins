package com.example.digitalcoins.viewmodel.firstfragmentviewmodel

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

class FirstFragmentViewModel(val userRepo: UserRepository) : ViewModel() {

    private val TAG: String = "HosseinZeinaliNesaz"
    private val firstInformation: MutableLiveData<User> = MutableLiveData()

    fun getFirstInformation(): LiveData<User> {
        return firstInformation
    }

    fun readFirstInformation() = viewModelScope.launch {
        val call = userRepo.readUserInfo()
        call.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                val getUsers = response.body()
                getUsers?.let {
                    firstInformation.value = it
                } ?: run {
                    firstInformation.value = null
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG,t.message.toString())
            }
        })
        /* userRepo.readUserInfo() // use flow
             .catch { e ->
                 Log.e(TAG, "get post ${e.message}")
             }
             .collect { response ->
                 firstInformation.value = response
             }*/
    }
}