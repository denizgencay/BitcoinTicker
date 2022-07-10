package com.example.bitcointicker.domain.repository

interface FirebaseRepository {

    fun signUp(email: String, password: String)
    fun login(email:String, password: String)
    fun addFavorite(coinId: String)
    fun removeFavorite(coinId: String)

}