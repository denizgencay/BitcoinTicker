package com.example.bitcointicker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointicker.R
import com.example.bitcointicker.domain.model.Coin

class CoinListRecyclerAdapter: RecyclerView.Adapter<CoinListRecyclerAdapter.CoinViewHolder>() {

    private var coins: List<Coin?> = listOf()
    private var listener: OnCardListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_card, parent,false)
        return CoinViewHolder(view, listener!!)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.nameText.text = coins[position]?.name.toString()
    }
    override fun getItemCount(): Int {
        return coins.size
    }

    class CoinViewHolder(view: View, listener: OnCardListener): RecyclerView.ViewHolder(view) {
        val nameText: TextView = itemView.findViewById(R.id.coin_name)
        init {
            itemView.setOnClickListener {
                listener.onCardClicked(position = adapterPosition)
            }
        }
    }

    fun setCoinListData(coinList: List<Coin>){
        this.coins = coinList
    }

    interface OnCardListener{
        fun onCardClicked(position: Int)
    }

    fun setOnCardClickedListener(listener: OnCardListener){
        this.listener = listener
    }
}