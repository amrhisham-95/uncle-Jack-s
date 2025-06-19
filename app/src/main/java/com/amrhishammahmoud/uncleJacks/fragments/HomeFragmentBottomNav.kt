package com.amrhishammahmoud.uncleJacks.fragments

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.ContentActivity
import com.amrhishammahmoud.uncleJacks.adapters.TabLayoutAdapter
import com.amrhishammahmoud.uncleJacks.databinding.FragmentHomeBottomNavBinding
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
        (activity as ContentActivity).supportActionBar?.title = ""


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout.addTab(tabLayout.newTab().setText("فواكه").setIcon(R.drawable.icon_fruits))
        tabLayout.addTab(tabLayout.newTab().setText("عصائر").setIcon(R.drawable.icon_juices))
        tabLayout.addTab(tabLayout.newTab().setText("ميكسات جاك").setIcon(R.drawable.icon_mixes))
        tabLayout.addTab(tabLayout.newTab().setText("آيس كريم").setIcon(R.drawable.icon_icecream))
        tabLayout.addTab(tabLayout.newTab().setText("فروت سلاط").setIcon(R.drawable.icon_fruitssalad))

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