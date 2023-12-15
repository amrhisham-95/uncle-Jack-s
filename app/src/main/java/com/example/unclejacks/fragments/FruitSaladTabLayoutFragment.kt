package com.example.unclejacks.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.unclejacks.R
import com.example.unclejacks.activities.ContentActivity
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.databinding.FragmentFruitSaladTabLayoutBinding
import com.example.unclejacks.databinding.FragmentFruitsTabLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FruitSaladTabLayoutFragment : Fragment() {

    private lateinit var binding : FragmentFruitSaladTabLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_fruit_salad_tab_layout,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()

        binding.apply {
            smallFruitSaladBtn.setOnClickListener {
                priceItemSalad.text = "30"
                typeSaladText.text= "Small"
                numTextSalad.text = "1"
                if (binding.numTextSalad.text.toString() == "1") {
                    binding.floatingActionButtonMinusSalad.visibility = View.GONE
                }

                floatingActionButtonPlusSalad.setOnClickListener {
                    binding.floatingActionButtonMinusSalad.visibility = View.VISIBLE

                    val xNum = numTextSalad.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt + 1

                    numTextSalad.text = yNumFinal.toString()

                    val finalPrice = 30 * yNumFinal
                    priceItemSalad.text = finalPrice.toString()
                }

                floatingActionButtonMinusSalad.setOnClickListener {
                    binding.floatingActionButtonMinusSalad.visibility = View.VISIBLE

                    val xNum = numTextSalad.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numTextSalad.text = yNumFinal.toString()

                    val finalPrice = 30 * yNumFinal
                    priceItemSalad.text = finalPrice.toString()

                    if (binding.numTextSalad.text.toString() == "1") {
                        binding.floatingActionButtonMinusSalad.visibility = View.GONE
                    }
                }
            }

            largeFruitSaladBtn.setOnClickListener {
                priceItemSalad.text = "40"
                typeSaladText.text= "Large"
                numTextSalad.text = "1"
                if (binding.numTextSalad.text.toString() == "1") {
                    binding.floatingActionButtonMinusSalad.visibility = View.GONE
                }

                floatingActionButtonPlusSalad.setOnClickListener {
                    binding.floatingActionButtonMinusSalad.visibility = View.VISIBLE

                    val xNum = numTextSalad.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt + 1

                    numTextSalad.text = yNumFinal.toString()

                    val finalPrice = 40 * yNumFinal
                    priceItemSalad.text = finalPrice.toString()
                }

                floatingActionButtonMinusSalad.setOnClickListener {
                    binding.floatingActionButtonMinusSalad.visibility = View.VISIBLE

                    val xNum = numTextSalad.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numTextSalad.text = yNumFinal.toString()

                    val finalPrice = 40 * yNumFinal
                    priceItemSalad.text = finalPrice.toString()

                    if (binding.numTextSalad.text.toString() == "1") {
                        binding.floatingActionButtonMinusSalad.visibility = View.GONE
                    }
                }
            }

            addToCartSaladBtn.setOnClickListener{
                if(typeSaladText.text.toString()=="Small"){
                    val x = numTextSalad.text.toString().toInt()
                    val y = priceItemSalad.text.toString().toInt()
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", (nameDishText.text.toString())+" "+(typeSaladText.text.toString()))
                    intent.putExtra("priceItem", y.toString())
                    intent.putExtra("picItem", R.drawable.fruitsaladsmall)
                    intent.putExtra("quantityItem", binding.numTextSalad.text.toString()+" Dish")
                    startActivity(intent)
                }
                if(typeSaladText.text.toString()=="Large"){
                    val x = numTextSalad.text.toString().toInt()
                    val y = priceItemSalad.text.toString().toInt()
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", (nameDishText.text.toString())+" "+(typeSaladText.text.toString()))
                    intent.putExtra("priceItem", y.toString())
                    intent.putExtra("picItem", R.drawable.fruitsaladlarge)
                    intent.putExtra("quantityItem", numTextSalad.text.toString()+" Dish")
                    startActivity(intent)
                }

            }


        }
    }

    private fun initSpinner(){

        val items = listOf("Select Your Favorite Dish","Mango","Kiwi","Watermelon","Pineapple","Strawberry","Pomegranate","Fruit-Salad")
        val myAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_dropdown_item,items)

        binding.spinnerFruitType.apply {
            adapter=myAdapter
            onItemSelectedListener =object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(items[p2]==items[0]){
                        binding.nameDishText.text=""
                    }else{
                        binding.nameDishText.text=items[p2]

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }
}