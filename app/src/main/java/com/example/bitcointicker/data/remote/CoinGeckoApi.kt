package com.example.bitcointicker.data.remote

import com.example.bitcointicker.domain.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinGeckoApi {
    @GET("coins/list")
    suspend fun getAllCoins(): List<Coin>

    @GET("coins/{id}")
    suspend fun getSelectedCoin(
        @Path("id") id: Int
    )
}