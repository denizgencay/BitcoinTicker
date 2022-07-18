package com.example.bitcointicker.util

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bitcointicker.domain.model.FavoriteCoin
import com.example.bitcointicker.domain.repository.DatabaseRepository
import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.example.bitcointicker.domain.repository.RemoteDataRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

@AndroidEntryPoint
class BackgroundService: Service() {

    @Inject
    lateinit var remoteDataRepository: RemoteDataRepository
    @Inject
    lateinit var firebaseRepository: FirebaseRepository
    @Inject
    lateinit var databaseRepository: DatabaseRepository

    private var _coin = MutableLiveData<List<String>>()
    private var _getFavoriteCoinsJob: Job? = null
    private var _coinDetailJob: Job? = null
    private val coin: LiveData<List<String>> = _coin

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(IO).launch {
            while (true){
                getFavoriteCoins()
                delay(3600000)
            }
        }
        return START_STICKY
    }


    private fun getFavoriteCoins(){
        _getFavoriteCoinsJob?.cancel()
        _getFavoriteCoinsJob = CoroutineScope(Main).launch {
            firebaseRepository.getFavoriteCoinIds().collect{
                _coin.postValue(it.data)
                getCoinDetail()
            }
        }
    }

    private fun getCoinDetail(){
        _coinDetailJob?.cancel()
        _coinDetailJob = CoroutineScope(Main).launch {
            for (item in coin.value!!){
                remoteDataRepository.getCoinDetail(item).collect{
                    val newCoin = FavoriteCoin(it.id!!, it.marketData!!.currentPrice!!.usd!!)
                    databaseRepository.addFavoriteCoin(newCoin)
                }
            }
        }
    }
}


































