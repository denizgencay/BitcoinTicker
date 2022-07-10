package com.example.bitcointicker.data.repository

import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.example.bitcointicker.util.Constants.FAVORITES
import com.example.bitcointicker.util.Constants.USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): FirebaseRepository {

    override fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    override fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
    }

    override fun addFavorite(coinId: String) {
        firebaseFirestore.collection(USER).document("firebaseAuth.uid!!").update(FAVORITES, FieldValue.arrayUnion(coinId)).addOnSuccessListener {
            println(it)
        }.addOnFailureListener{
            println(it.localizedMessage)
        }
    }

    override fun removeFavorite(coinId: String) {
        firebaseFirestore.collection(USER).document(firebaseAuth.uid!!).update(FAVORITES, FieldValue.arrayRemove(coinId))
    }
}