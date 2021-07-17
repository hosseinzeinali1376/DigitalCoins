package com.example.digitalcoins.model.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_information_sell")
class Item_Sell(
    @PrimaryKey(autoGenerate = true)
    var id_sell: Int,
    @ColumnInfo(name = "Sum_sell")
    var sum_sell: String? = null,

    @ColumnInfo(name = "Amount_sell")
    var amount_sell: String? = null,

    @ColumnInfo(name = "Date_sell")
    var date_sell: String? = null,

    @ColumnInfo(name = "Kind_sell")
    var kind_sell: String? = null,

    @ColumnInfo(name = "Status")
    var status: Int? = null)
{
    constructor(sum: String?, amount: String?, date: String?, kind: String?,status: Int?) : this(
        0,
        sum,
        amount,
        date,
        kind,
        status
    )
}