package com.example.bitcointicker.data.repository

import androidx.lifecycle.LiveData
import com.example.bitcointicker.data.local.CoinDatabase
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.repository.DatabaseRepository
import com.example.bitcointicker.util.Resource
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    coinDatabase: CoinDatabase
): DatabaseRepository {
    private val coinDao = coinDatabase.coinDao()

    override suspend fun addAllCoins(coinList: List<Coin>) = coinDao.addAllCoins(coinList)

    override fun getAllCoins(): LiveData<List<Coin>> = coinDao.getAllCoins()

    override fun searchCoin(query: String): LiveData<List<Coin>> = coinDao.searchCoins(query)


}