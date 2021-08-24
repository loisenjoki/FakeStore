package com.example.fakestore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.fakestore.R
import com.example.fakestore.ui.Fragments.AllProducts
import com.example.fakestore.ui.Fragments.AllProductsAdapterFragment
import com.example.fakestore.ui.Fragments.ElectronicsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var allProductsFragment: AllProductsAdapterFragment
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        allProductsFragment = AllProductsAdapterFragment(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = allProductsFragment
         tabLayout = view.findViewById(R.id.tab_layout)
        allProductsFragment.itemCount

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = allProductsFragment.getPageTitle(position);
        }.attach()
    }
}