package com.example.bitcointicker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcointicker.data.local.dao.CoinDao
import com.example.bitcointicker.data.local.dao.FavoriteDao
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.model.FavoriteCoin

@Database(entities = [Coin::class, FavoriteCoin::class], version = 1, exportSchema = false)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
    abstract fun favoriteDao(): FavoriteDao
}