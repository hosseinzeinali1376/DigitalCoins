package com.example.digitalcoins.model.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_information_buy")
class Item_Buy(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "Sum")
    var sum: String? = null,

    @ColumnInfo(name = "Amount")
    var amount: String? = null,

    @ColumnInfo(name = "Date")
    var date: String? = null,

    @ColumnInfo(name = "Kind")
    var kind: String? = null,

    @ColumnInfo(name = "Status")
    var status: Int? = null
) {
    constructor(
        sum: String?,
        amount: String?,
        date: String?,
        kind: String?,
        status: Int?
    ) : this(
        0,
        sum,
        amount,
        date,
        kind,
        status
    )
}