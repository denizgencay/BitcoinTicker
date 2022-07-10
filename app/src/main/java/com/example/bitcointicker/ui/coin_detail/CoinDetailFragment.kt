package com.example.bitcointicker.ui.coin_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bitcointicker.databinding.FragmentCoinDetailBinding
import com.example.bitcointicker.domain.model.CoinDetail
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailBinding
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater,container,false)
        coinDetailViewModel.getCoinDetail()
        val dataObserver = Observer<CoinDetail>{
            binding.description.text = it.name
        }
        coinDetailViewModel.coinDetail.observe(viewLifecycleOwner,dataObserver)
        binding.favoriteButton.setOnClickListener{
            coinDetailViewModel.addFavorite("starbase")
        }
        Picasso.get().load("https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png?1547033579").into(binding.coinImage);
        return binding.root
    }
}