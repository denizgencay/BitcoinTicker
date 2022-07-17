package com.example.bitcointicker.ui.home.all_coins

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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
        observeData()
        configureSearchView()
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

    private fun observeData(){
        allCoinsViewModel.getAllCoins()
        allCoinsViewModel.getAllCoinsFromDb.observe(viewLifecycleOwner){
            recyclerAdapter.setCoinListData(it)
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun configureSearchView(){
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchDatabase(s.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"
        allCoinsViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                println("searched coin $it")
                recyclerAdapter.setCoinListData(it)
                recyclerAdapter.notifyDataSetChanged()
            }
        }
    }

}














































