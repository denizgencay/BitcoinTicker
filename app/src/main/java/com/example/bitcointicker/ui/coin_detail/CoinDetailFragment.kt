package com.example.bitcointicker.ui.coin_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.bitcointicker.databinding.FragmentCoinDetailBinding
import com.example.bitcointicker.domain.model.CoinDetail
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val args: CoinDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCoinDetailBinding
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(inflater,container,false)
        initViewModel(args.coinId)
        return binding.root
    }

    private fun initViewModel(id: String){
        coinDetailViewModel.getCoinDetail(id)
        coinDetailViewModel.coinDetail.observe(viewLifecycleOwner){
            binding.description.text = it.name
            binding.currentPrice.text = it.marketData!!.currentPrice?.usd.toString()
            binding.priceChange.text = it.marketData!!.priceChange24h.toString()
            binding.hashingAlgorithm.text = it.hashingAlgorithm.toString()
            loadImage(it.image!!.large!!)
        }
        binding.favoriteButton.setOnClickListener{
            coinDetailViewModel.addFavorite(id)
        }

    }

    private fun loadImage(url: String){
        Picasso
            .get()
            .load(url)
            .into(binding.coinImage);
    }
}