package com.example.digitalcoins.model.items.coinsitems

import com.google.gson.annotations.SerializedName

open class User(
    @SerializedName("RAW")
    val raw: RAW = RAW()
)