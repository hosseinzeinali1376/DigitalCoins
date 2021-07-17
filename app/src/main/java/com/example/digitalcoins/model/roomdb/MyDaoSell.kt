package com.example.digitalcoins.model.roomdb

import androidx.room.*
import com.example.digitalcoins.model.items.Item_Sell


@Dao
interface MyDaoSell {

    @Insert
    fun _addInformationSell(itemSell: Item_Sell)

    @Query("select * from item_information_sell")
    fun _getInformationSell(): List<Item_Sell>

    @Delete
    fun _deleteInformationSell(itemSell: Item_Sell)

    @Update
    fun _updateInformationSell(itemSell: Item_Sell)

    @Query("SELECT * FROM item_information_sell WHERE id_sell == :id")
    fun isProductExistSell(id: Int): Int
}