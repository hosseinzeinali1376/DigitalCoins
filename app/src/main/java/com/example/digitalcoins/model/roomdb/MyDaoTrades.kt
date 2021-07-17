package com.example.digitalcoins.model.roomdb

import androidx.room.*
import com.example.digitalcoins.model.items.Item_Trades


@Dao
interface MyDaoTrades {
    @Insert
    fun _addInformationTrades(itemTrades: Item_Trades)

    @Query("select * from item_information_trades")
    fun _getInformationTrades(): List<Item_Trades>

    @Delete
    fun _deleteInformationTrades(itemTrades: Item_Trades)

    @Update
    fun _updateInformationTrades(itemTrades: Item_Trades)

    @Query("SELECT * FROM item_information_trades WHERE id_trades == :id_trades")
    fun isProductExistTrades(id_trades: Int): Int
}