package com.example.bitcointicker.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bitcointicker.databinding.FragmentHomeBinding
import com.example.bitcointicker.ui.adapter.ViewPagerAdapter
import com.example.bitcointicker.ui.home.favorite.FavoriteFragment
import com.example.bitcointicker.ui.home.all_coins.AllCoinsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        configureViewPager()
        return binding.root
    }

    private fun configureViewPager(){
        val adapter = ViewPagerAdapter(activity)
        adapter.addFragment(AllCoinsFragment(), "All Coins")
        adapter.addFragment(FavoriteFragment(), "Favorites")
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = 0
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

}