package com.example.unclejacks.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.unclejacks.R
import com.example.unclejacks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

    }


    override fun onResume() {
        super.onResume()
        //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
        setSupportActionBar(binding.toolbar)
        binding.apply {
            toolbar.title = "uncle Jack's"
            toolbar.setTitleTextColor(Color.WHITE)
        }

    }
}