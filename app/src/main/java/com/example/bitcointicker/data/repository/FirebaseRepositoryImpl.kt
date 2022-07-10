package com.example.bitcointicker.data.repository

import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.example.bitcointicker.util.Constants.FAVORITES
import com.example.bitcointicker.util.Constants.USER
import com.example.bitcointicker.util.Resource
import com.example.bitcointicker.util.toast_message_helper.ToastMessageHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val toastMessageHelper: ToastMessageHelper
): FirebaseRepository {

    override fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener{
            toastMessageHelper.showToastMessage(it.localizedMessage)
        }
    }

    override fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Resource.success(true)
        }.addOnFailureListener{
            toastMessageHelper.showToastMessage(it.localizedMessage)
        }
    }

    override fun addFavorite(coinId: String) {
        firebaseFirestore.collection(USER).document("firebaseAuth.uid!!").update(FAVORITES, FieldValue.arrayUnion(coinId)).addOnSuccessListener {
            println(it)
        }.addOnFailureListener{
            toastMessageHelper.showToastMessage(it.localizedMessage)
        }
    }

    override fun removeFavorite(coinId: String) {
        firebaseFirestore.collection(USER).document("firebaseAuth.uid!!").update(FAVORITES, FieldValue.arrayRemove(coinId)).addOnFailureListener{
            toastMessageHelper.showToastMessage(it.localizedMessage)
        }
    }

    override fun getFavoriteCoinIds(): Flow<List<String>> =
        callbackFlow {
            try {
                firebaseFirestore.collection(USER).document("firebaseAuth.uid!!").addSnapshotListener{ data, _ ->
                    trySend(data?.get("favorites") as List<String>)
                    close()
                }
            }catch (e: Exception){
                toastMessageHelper.showToastMessage(e.localizedMessage)
                close()
            }
        }
}