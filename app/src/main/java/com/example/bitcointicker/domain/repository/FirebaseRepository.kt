package com.example.bitcointicker.domain.repository

import com.example.bitcointicker.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun signUp(email: String, password: String): Flow<Resource<Boolean>>
    suspend fun loginUser(email:String, password: String): Flow<Resource<Boolean>>
    fun addFavorite(coinId: String, currentPrice: Double)
    suspend fun getFavoriteCoinIds(): Flow<Resource<List<String>>>

}