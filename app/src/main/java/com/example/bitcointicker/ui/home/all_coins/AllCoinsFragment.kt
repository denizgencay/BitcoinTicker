package com.example.bitcointicker.ui.home.all_coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcointicker.R
import com.example.bitcointicker.databinding.FragmentAllCoinsFragementBinding
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.ui.adapter.CoinListRecyclerAdapter
import com.example.bitcointicker.ui.adapter.CoinListRecyclerAdapter.OnCardListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCoinsFragment : Fragment() {

    private lateinit var binding: FragmentAllCoinsFragementBinding
    private val allCoinsViewModel: AllCoinsViewModel by viewModels()
    private val recyclerAdapter = CoinListRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCoinsFragementBinding.inflate(inflater,container,false)
        configureRecyclerView()
        allCoinsViewModel.getAllCoins()
        val coinObserver = Observer<List<Coin>>{
            recyclerAdapter.setCoinListData(it)
            recyclerAdapter.notifyDataSetChanged()
        }
        allCoinsViewModel.getAllCoinsFromDb.observe(viewLifecycleOwner,coinObserver)
        return binding.root
    }

    private fun configureRecyclerView(){
        binding.allCoinsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.allCoinsRecyclerView.adapter = recyclerAdapter
        recyclerAdapter.setOnCardClickedListener(object : OnCardListener {
            override fun onCardClicked(position: Int) {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_coinDetailFragment)
            }
        })
    }

}