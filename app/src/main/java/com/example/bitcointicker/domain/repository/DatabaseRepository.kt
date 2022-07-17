package com.example.bitcointicker.domain.repository

import androidx.lifecycle.LiveData
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.util.Resource
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun addAllCoins(coinList: List<Coin>)
    fun getAllCoins(): LiveData<List<Coin>>
}