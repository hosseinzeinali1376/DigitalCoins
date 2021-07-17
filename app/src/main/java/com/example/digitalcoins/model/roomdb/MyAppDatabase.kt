package com.example.digitalcoins.model.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digitalcoins.model.items.Item_Buy
import com.example.digitalcoins.model.items.Item_Coin
import com.example.digitalcoins.model.items.Item_Sell
import com.example.digitalcoins.model.items.Item_Trades


@Database(
    entities = [Item_Buy::class, Item_Sell::class, Item_Trades::class, Item_Coin::class],
    version = 12
)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
    abstract fun myDaoSell(): MyDaoSell
    abstract fun myDaoTrades(): MyDaoTrades
    abstract fun myDaoSaveCoinPrice(): MyDaoSaveCoinPrice
}