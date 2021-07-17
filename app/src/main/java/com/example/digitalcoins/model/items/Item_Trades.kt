package com.example.digitalcoins.model.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_information_trades")
class Item_Trades(
    @PrimaryKey
    var id_trades: Int? = null,

    @ColumnInfo(name = "cardNumberOrgin")
    var cardNumberOrgin: String? = null,

    @ColumnInfo(name = "cardNumberDestination")
    var cardNumberDestination: String? = null,

    @ColumnInfo(name = "userName")
    var userName: String? = null,

    @ColumnInfo(name = "amountOfMoneyTransfer")
    var amountOfMoneyTransfer: String? = null,

    @ColumnInfo(name = "secondPassword")
    var secondPassword: String? = null,

    @ColumnInfo(name = "SecondIdentificationNumber")
    var SecondIdentificationNumber: String? = null,

    @ColumnInfo(name = "years")
    var years: String? = null,

    @ColumnInfo(name = "month")
    var month: String? = null
)