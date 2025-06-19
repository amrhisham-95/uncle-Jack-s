package com.amrhishammahmoud.uncleJacks.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.ContentActivity
import com.amrhishammahmoud.uncleJacks.databinding.FragmentCategoriesBottomNavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragmentBottomNav : Fragment() {

    private lateinit var binding : FragmentCategoriesBottomNavBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_categories_bottom_nav,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = ""


        binding.apply {
            imageButtonFruits.setOnClickListener {
                imageButtonFruits.visibility=View.GONE
                imageButtonJuices.visibility=View.GONE
                imageButtonFruitSalad.visibility=View.GONE
                imageButtonIcecream.visibility=View.GONE
                imageButtonMixes.visibility=View.GONE

                replaceFragment(FruitsTabLayoutFragment())
            }
            imageButtonJuices.setOnClickListener {
                imageButtonFruits.visibility=View.GONE
                imageButtonJuices.visibility=View.GONE
                imageButtonFruitSalad.visibility=View.GONE
                imageButtonIcecream.visibility=View.GONE
                imageButtonMixes.visibility=View.GONE

                replaceFragment(JuicesTabLayoutFragment())
            }
            imageButtonIcecream.setOnClickListener {
                imageButtonFruits.visibility=View.GONE
                imageButtonJuices.visibility=View.GONE
                imageButtonFruitSalad.visibility=View.GONE
                imageButtonIcecream.visibility=View.GONE
                imageButtonMixes.visibility=View.GONE

                replaceFragment(IcecreamTabLayoutFragment())
            }
            imageButtonFruitSalad.setOnClickListener {
                imageButtonFruits.visibility=View.GONE
                imageButtonJuices.visibility=View.GONE
                imageButtonFruitSalad.visibility=View.GONE
                imageButtonIcecream.visibility=View.GONE
                imageButtonMixes.visibility=View.GONE

                replaceFragment(FruitSaladTabLayoutFragment())
            }

            imageButtonMixes.setOnClickListener {
                imageButtonFruits.visibility=View.GONE
                imageButtonJuices.visibility=View.GONE
                imageButtonFruitSalad.visibility=View.GONE
                imageButtonIcecream.visibility=View.GONE
                imageButtonMixes.visibility=View.GONE

                replaceFragment(JacksMixesFragment())
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    //to open specific fragment in the frame layout inside the activity
    private fun replaceFragment (fragment : Fragment){
        val fragmentManager = (activity as ContentActivity).supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.categoriesFrame,fragment)
        fragmentTransition.commit()
    }


}