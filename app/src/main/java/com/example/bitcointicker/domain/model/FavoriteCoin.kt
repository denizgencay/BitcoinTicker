package com.example.bitcointicker.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitcointicker.util.Constants.FAVORITE_COINS_TABLE

@Entity(tableName = FAVORITE_COINS_TABLE)
data class FavoriteCoin(
    @NonNull
    @PrimaryKey
    val id: String = "",
    val currentPrice: Double = 0.0
)
