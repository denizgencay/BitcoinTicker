package com.example.bitcointicker.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitcointicker.util.Constants.ALL_COINS_TABLE

@Entity(tableName = ALL_COINS_TABLE)
data class Coin(
    @NonNull
    @PrimaryKey
    val id: String = "",
    val symbol: String? = "",
    val name: String?= ""
)
