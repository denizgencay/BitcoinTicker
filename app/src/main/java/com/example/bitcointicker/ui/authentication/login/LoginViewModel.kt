package com.example.bitcointicker.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointicker.data.repository.FirebaseRepositoryImpl
import com.example.bitcointicker.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
): ViewModel() {
    private var _loginJob: Job? = null
    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

    fun loginUser(email: String, password: String){
        _loginJob?.cancel()
        _loginJob = viewModelScope.launch{
            firebaseRepository.loginUser(email,password).collect{
                _result.value = it.data
            }
        }
    }
}