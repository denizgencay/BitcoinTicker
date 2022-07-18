package com.example.bitcointicker.ui.authentication.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointicker.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): ViewModel() {
    private var _signUpJob: Job? = null
    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

    fun signUpUser(email: String, password: String){
        _signUpJob?.cancel()
        _signUpJob = viewModelScope.launch {
            firebaseRepository.signUp(email,password).collect{
                _result.value = it.data
            }
        }
    }
}