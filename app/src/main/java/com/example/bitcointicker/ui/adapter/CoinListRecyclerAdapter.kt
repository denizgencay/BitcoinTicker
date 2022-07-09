package com.example.bitcointicker.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointicker.domain.model.Coin

class CoinListRecyclerAdapter: RecyclerView.Adapter<CoinListRecyclerAdapter.CoinViewHolder>() {

    private var coins: List<Coin?> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    override fun getItemCount(): Int {
        return coins.size
    }

    class CoinViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun setCoinListData(coinList: List<Coin>){
        this.coins = coinList
    }
}