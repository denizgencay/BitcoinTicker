package com.example.bitcointicker.ui.home.all_coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.repository.DatabaseRepository
import com.example.bitcointicker.domain.repository.RemoteDataRepository
import com.example.bitcointicker.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCoinsViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository,
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    private var _getAllCoinsJob: Job? = null
    private var _addAllCoinsJob: Job? = null

    val getAllCoinsFromDb: LiveData<List<Coin>> = databaseRepository.getAllCoins()

    fun searchDatabase(query: String): LiveData<List<Coin>>{
        return databaseRepository.searchCoin(query)
    }

    fun getAllCoins(){
        _getAllCoinsJob?.cancel()
        _getAllCoinsJob = viewModelScope.launch{
            remoteDataRepository.getAllCoins().collect{
                addAllCoins(it.data!!)
            }
        }
    }
    private fun addAllCoins(coinList: List<Coin>){
        _addAllCoinsJob?.cancel()
        _addAllCoinsJob = viewModelScope.launch{
            databaseRepository.addAllCoins(coinList)
        }
    }
}





























