package com.example.bitcointicker.data.repository

import com.example.bitcointicker.data.local.CoinDatabase
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    coinDatabase: CoinDatabase,
): DatabaseRepository {
    private val coinDao = coinDatabase.coinDao()
    override suspend fun addAllCoins(coinList: List<Coin>) {
        coinDao.addAllCoins(coinList)
    }

}