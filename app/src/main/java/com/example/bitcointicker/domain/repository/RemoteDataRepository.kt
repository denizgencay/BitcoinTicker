package com.example.bitcointicker.domain.repository

import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.model.CoinDetail
import com.example.bitcointicker.util.Resource
import kotlinx.coroutines.flow.Flow


interface RemoteDataRepository {
    suspend fun getAllCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinDetail(id: String): Flow<CoinDetail>
}