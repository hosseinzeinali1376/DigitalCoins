package com.example.digitalcoins.model.items.coinsitems

import com.google.gson.annotations.SerializedName

data class BTC (
    @SerializedName("USD")
    val usd: USD = USD()
        )