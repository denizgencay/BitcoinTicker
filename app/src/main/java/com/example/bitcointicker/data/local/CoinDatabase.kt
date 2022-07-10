package com.example.bitcointicker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcointicker.data.local.dao.CoinDao
import com.example.bitcointicker.domain.model.Coin

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}