package com.example.digitalcoins.model.items.coinsitems

import com.google.gson.annotations.SerializedName

class USD(
    @SerializedName("FROMSYMBOL")
    val symbole: String = "",
    @SerializedName("PRICE")
    val price: Double = 0.0,
    @SerializedName("CHANGE24HOUR")
    val change24hour: Float? = null
)