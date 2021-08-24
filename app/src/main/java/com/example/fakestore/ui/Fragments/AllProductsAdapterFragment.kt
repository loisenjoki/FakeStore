package com.example.fakestore.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fakestore.utils.Constants.Companion.ARG_OBJECT

class AllProductsAdapterFragment(fragment: Fragment) : FragmentStateAdapter(fragment) {

            override fun getItemCount(): Int = 5

            override fun createFragment(position: Int): Fragment {
                // Return a NEW fragment instance in createFragment(int)
                val fragment  = getItem(position)
                if (fragment != null) {
                    fragment.arguments = Bundle().apply {
                        // Our object is just an integer :-P
                        putInt(ARG_OBJECT, position)
                    }
                }
                return fragment!!
            }
    fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                AllProducts()
            }
            1 -> {
                ElectronicsFragment()
            }
            2 -> {
                jeweleryFragment()
            }
            3 -> {
                WomenFragment()
            }
            4 -> {
                MenFragment()
            }
            else -> AllProducts()
        }
    }
    fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "All products"
            1 -> return "Electronics"
            2 -> return "Jewelery"
            3 -> return "Women Cloths"
            4 -> return "Men Cloths"
        }
        return null
    }

        }
