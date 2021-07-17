package com.example.digitalcoins.model.roomdb

import androidx.room.*
import com.example.digitalcoins.model.items.Item_Buy


@Dao
interface MyDao {

    @Insert
    fun _addInformation(itemBuy: Item_Buy)

    @Query("select * from item_information_buy")
    fun _getInformation(): List<Item_Buy>

    @Delete
    fun _deleteInformation(itemBuy: Item_Buy)

    @Update
    fun _updateInformation(itemBuy: Item_Buy)

    @Query("SELECT * FROM item_information_buy WHERE id == :id")
    fun isProductExistBuy(id: Int): Int

    @Query("SELECT * FROM item_information_buy WHERE id ")
    fun isIdExistBuy(): Int

    @Query("SELECT * FROM item_information_buy WHERE Kind == :Kind")
    fun selectWithName(Kind : String): List<Item_Buy>
}