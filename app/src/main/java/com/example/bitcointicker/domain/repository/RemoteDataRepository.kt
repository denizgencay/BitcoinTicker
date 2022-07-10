package com.example.bitcointicker.domain.repository

import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow


interface RemoteDataRepository {
    suspend fun getAllCoins(): Flow<List<Coin>>
    suspend fun getCoinDetail(id: String): Flow<CoinDetail>
}