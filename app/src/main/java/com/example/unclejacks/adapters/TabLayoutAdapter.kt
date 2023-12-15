package com.example.unclejacks.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.unclejacks.fragments.*

internal class TabLayoutAdapter(var context : Context, fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {FruitsTabLayoutFragment()}
            1 -> {JuicesTabLayoutFragment()}
            2 -> {IcecreamTabLayoutFragment()}
            else -> {FruitSaladTabLayoutFragment()}
        }
    }

}