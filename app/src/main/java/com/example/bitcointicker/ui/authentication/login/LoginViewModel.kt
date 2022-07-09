package com.example.bitcointicker.ui.authentication.login

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.data.repository.FirebaseRepositoryImpl
import com.example.bitcointicker.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): ViewModel() {
    fun loginUser(email: String, password: String){
        firebaseRepository.login(email,password)
    }
}