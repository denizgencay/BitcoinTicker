package com.example.bitcointicker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitcointicker.domain.model.Coin

@Dao
interface CoinDao {
    @Query("SELECT * FROM ALL_COINS_TABLE")
    fun getAllCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM ALL_COINS_TABLE WHERE name LIKE :searchQuery")
    fun searchCoins(searchQuery: String): LiveData<List<Coin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCoins(coins: List<Coin>)
}