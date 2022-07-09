package com.example.bitcointicker.ui.authentication.sign_up

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): ViewModel() {
    fun signUpUser(email: String, password: String){
        firebaseRepository.signUp(email,password)
    }
}