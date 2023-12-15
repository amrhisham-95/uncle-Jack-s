package com.example.unclejacks.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.unclejacks.R
import com.example.unclejacks.activities.ContentActivity
import com.example.unclejacks.adapters.TabLayoutAdapter
import com.example.unclejacks.databinding.FragmentHomeBottomNavBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragmentBottomNav : Fragment() {

    private lateinit var binding :FragmentHomeBottomNavBinding
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home_bottom_nav,container,false)
        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = "Home"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout.addTab(tabLayout.newTab().setText("Fruits"))
        tabLayout.addTab(tabLayout.newTab().setText("Juices"))
        tabLayout.addTab(tabLayout.newTab().setText("Icecream"))
        tabLayout.addTab(tabLayout.newTab().setText("Fruit Salad"))


        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val tabLayoutAdapter =
            TabLayoutAdapter(requireContext(), childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = tabLayoutAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }
}