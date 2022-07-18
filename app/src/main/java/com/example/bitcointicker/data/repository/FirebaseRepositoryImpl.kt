package com.example.bitcointicker.data.repository

import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.example.bitcointicker.util.Constants.FAVORITES
import com.example.bitcointicker.util.Constants.USER
import com.example.bitcointicker.util.Resource
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import com.example.bitcointicker.util.toast_message_helper.ToastMessageHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val toastMessageHelper: ToastMessageHelper,
    private val dispatcherProvider: DispatcherProvider
): FirebaseRepository {

    override suspend fun signUp(email: String, password: String): Flow<Resource<Boolean>> =
       callbackFlow {
           try {
               val signUp = firebaseAuth.createUserWithEmailAndPassword(email,password)
               if (signUp.isSuccessful){
                   val map: HashMap<String, List<String>> = hashMapOf()
                   map["favorites"] = listOf()
                   firebaseFirestore.collection("user").document(firebaseAuth.currentUser!!.uid).set(map)
                   trySend(Resource.success(true))
                   close()
               }else{
                   toastMessageHelper.showToastMessage(signUp.exception!!.localizedMessage!!)
                   trySend(Resource.error(false,signUp.exception!!.localizedMessage!!))
                   close()
               }
           }catch (e: Exception){
               close()
           }
       }.flowOn(dispatcherProvider.mainImmediate)


    override suspend fun loginUser(email: String, password: String): Flow<Resource<Boolean>> =
        callbackFlow {
            try {
                val login = firebaseAuth.signInWithEmailAndPassword(email,password)
                if (login.isSuccessful){
                    trySend(Resource.success(true))
                }else{
                    toastMessageHelper.showToastMessage(login.exception!!.localizedMessage!!)
                    trySend(Resource.error(false,login.exception!!.localizedMessage!!))
                }
                close()
            }catch (e: Exception){
                close()
            }
        }.flowOn(dispatcherProvider.mainImmediate)



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

    override suspend fun getFavoriteCoinIds(): Flow<Resource<List<String>>> =
        callbackFlow {
            val response = firebaseFirestore.collection(USER).document("firebaseAuth.uid!!").get().await()
            if (response.exists()){
                trySend(Resource.success(response.data!!["favorites"] as List<String>))
            }else{
                trySend(Resource.error(listOf(),"Unexpected Error"))
            }
            close()
        }.flowOn(dispatcherProvider.io)

}

























