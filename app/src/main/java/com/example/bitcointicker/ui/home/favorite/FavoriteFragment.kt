package com.example.bitcointicker.ui.home.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcointicker.databinding.FragmentFavoriteBinding
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.ui.adapter.CoinListRecyclerAdapter
import com.example.bitcointicker.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val recyclerAdapter = CoinListRecyclerAdapter()
    private val listForReturn = arrayListOf<Coin>()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        configureRecyclerView()
        observeData()
        configureSwipeToRefresh()
        return binding.root
    }


    private fun observeData(){
        favoriteViewModel.getFavoriteCoins()
        favoriteViewModel.getAllCoinsFromDb.observe(viewLifecycleOwner){ coinList ->
            favoriteViewModel.coin.observe(viewLifecycleOwner){ favoriteList ->
                sortFavoriteList(coinList, favoriteList)
                recyclerAdapter.setCoinListData(listForReturn)
                recyclerAdapter.notifyDataSetChanged()
                binding.swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun configureRecyclerView(){
        binding.favoriteCoinsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoriteCoinsRecyclerView.adapter = recyclerAdapter
        recyclerAdapter.setOnCardClickedListener(object : CoinListRecyclerAdapter.OnCardListener {
            override fun onCardClicked(position: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(listForReturn[position].id.trim())
                view?.findNavController()?.navigate(action)
            }
        })
    }

    private fun configureSwipeToRefresh(){
        binding.swipeToRefresh.setOnRefreshListener{
            observeData()
        }
    }

    private fun sortFavoriteList(coinList: List<Coin>, favoriteList: List<String>){
        for (coin in coinList){
            for (favorite in favoriteList){
                if (coin.id == favorite && listForReturn.size < favoriteList.size){
                    listForReturn.add(coin)
                }
            }
        }
    }

}



























