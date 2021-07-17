package com.example.digitalcoins.model.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "save_coin_price")
data class Item_Coin(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "price")
    var price: Double,
    @ColumnInfo(name = "symbole")
    var symbole: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "change24hour")
    var change24hour: Double
)