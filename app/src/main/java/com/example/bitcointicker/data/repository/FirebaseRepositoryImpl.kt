package com.example.bitcointicker.data.repository

import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseRepository {
    override fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    override fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
    }
}