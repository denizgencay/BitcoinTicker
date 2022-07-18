package com.example.bitcointicker.ui.home.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.repository.DatabaseRepository
import com.example.bitcointicker.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    private var _getFavoriteCoinsJob: Job? = null
    private var _coin = MutableLiveData<List<String>>()
    val coin: LiveData<List<String>> = _coin
    val getAllCoinsFromDb: LiveData<List<Coin>> = databaseRepository.getAllCoins()

    fun getFavoriteCoins(){
        _getFavoriteCoinsJob?.cancel()
        _getFavoriteCoinsJob = viewModelScope.launch {
            firebaseRepository.getFavoriteCoinIds().collect{
                _coin.value = it.data
            }
        }

    }

}