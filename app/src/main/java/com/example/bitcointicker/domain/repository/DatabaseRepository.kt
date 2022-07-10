package com.example.bitcointicker.domain.repository

import com.example.bitcointicker.domain.model.Coin

interface DatabaseRepository {
    suspend fun addAllCoins(coinList: List<Coin>)
}