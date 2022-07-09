package com.example.bitcointicker.data.repository

import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseRepository {
    override fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            println("suc")
        }.addOnFailureListener{
            println(it.localizedMessage)
        }
    }

    override fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

        }.addOnFailureListener{

        }
    }
}