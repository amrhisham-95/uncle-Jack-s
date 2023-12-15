package com.example.unclejacks.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.unclejacks.R
import com.example.unclejacks.databinding.FragmentWelcomingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomingFragment : Fragment() {

    private lateinit var binding :FragmentWelcomingBinding

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
                binding= DataBindingUtil.inflate(inflater,R.layout.fragment_welcoming,container,false)
                return binding.root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skipBtn.setOnClickListener {
            binding.apply {
                findNavController().navigate(R.id.action_welcomingFragment_to_loginFragment)
            }
        }

    }
}