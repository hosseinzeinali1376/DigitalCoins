package com.example.digitalcoins.model.items.coinsitems

import com.google.gson.annotations.SerializedName

class RAW(
    @SerializedName("BTC")
    val btc: BTC = BTC(),
    @SerializedName("ETH")
    val eth: BTC = BTC(),
    @SerializedName("BNB")
    val bnb: BTC = BTC(),
    @SerializedName("XRP")
    val xrp: BTC = BTC(),
    @SerializedName("XLM")
    val xlm: BTC = BTC(),
    @SerializedName("LTC")
    val ltc: BTC = BTC(),
    @SerializedName("ETC")
    val etc: BTC = BTC(),
    @SerializedName("DOGE")
    val doeg: BTC = BTC(),
    @SerializedName("EOS")
    val eos: BTC = BTC(),
    @SerializedName("BUSD")
    val busd: BTC = BTC()
)