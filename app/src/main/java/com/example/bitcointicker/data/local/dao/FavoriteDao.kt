package com.example.bitcointicker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitcointicker.domain.model.FavoriteCoin

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FAVORITE_COINS_TABLE")
    fun getAllFavoriteCoins(): LiveData<List<FavoriteCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllFavoriteCoins(coins: List<FavoriteCoin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoin(coin: FavoriteCoin)

}