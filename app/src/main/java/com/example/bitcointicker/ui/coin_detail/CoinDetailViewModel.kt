package com.example.bitcointicker.ui.coin_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointicker.domain.model.CoinDetail
import com.example.bitcointicker.domain.repository.RemoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository
): ViewModel() {
    private var _coinDetailJob: Job? = null
    private var _coinDetail = MutableLiveData<CoinDetail>()
    val coinDetail: LiveData<CoinDetail> = _coinDetail

    fun getCoinDetail(){
        _coinDetailJob?.cancel()
        _coinDetailJob = viewModelScope.launch {
            remoteDataRepository.getCoinDetail("starbase").collect{
                _coinDetail.value = it
                println(it)
            }
        }
    }

}