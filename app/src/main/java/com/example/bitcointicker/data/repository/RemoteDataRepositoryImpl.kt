package com.example.bitcointicker.data.repository

import com.example.bitcointicker.data.remote.CoinGeckoApi
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.model.CoinDetail
import com.example.bitcointicker.domain.repository.RemoteDataRepository
import com.example.bitcointicker.util.Resource
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val coinGeckoApi: CoinGeckoApi,
    private val dispatcherProvider: DispatcherProvider
): RemoteDataRepository {
    override suspend fun getAllCoins(): Flow<Resource<List<Coin>>> =
        callbackFlow {
            try {
                val response = coinGeckoApi.getAllCoins()
                trySend(Resource.success(response))
                close()
            }catch (e: Exception){
                close()
            }
        }.flowOn(dispatcherProvider.io)

    override suspend fun getCoinDetail(id: String): Flow<CoinDetail> =
        callbackFlow {
            try {
                val response = coinGeckoApi.getSelectedCoin(id)
                trySend(response)
                println(response)
                close()
            }catch (e: Exception){
                println(e.localizedMessage)
                close()
            }
        }.flowOn(dispatcherProvider.io)
}