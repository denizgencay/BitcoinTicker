package com.example.bitcointicker.domain.repository

import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun signUp(email: String, password: String)
    fun login(email:String, password: String)
    fun addFavorite(coinId: String)
    fun removeFavorite(coinId: String)
    fun getFavoriteCoinIds(): Flow<List<String>>

}