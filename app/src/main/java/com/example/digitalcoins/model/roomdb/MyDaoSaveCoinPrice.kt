package com.example.digitalcoins.model.roomdb

import androidx.room.*
import com.example.digitalcoins.model.items.Item_Coin

@Dao
interface MyDaoSaveCoinPrice {
    @Insert
    fun _addCoin(itemCoin: Item_Coin)

    @Query("select * from save_coin_price")
    fun _getCoin(): List<Item_Coin>

    @Delete
    fun _deleteCoin(itemCoin: Item_Coin)

    @Update
    fun _updateCoin_(itemCoin: Item_Coin)

    @Query("SELECT * FROM save_coin_price WHERE id == :id")
    fun isProductExistCoin(id: Int): Boolean


    @Query("SELECT * FROM save_coin_price WHERE name == :name")
    fun selectWithName(name : String): List<Item_Coin>
}