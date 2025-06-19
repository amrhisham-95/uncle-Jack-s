package com.amrhishammahmoud.uncleJacks.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.MyCartActivity
import com.amrhishammahmoud.uncleJacks.databinding.FragmentFruitSaladTabLayoutBinding
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


        binding.apply {
            smallFruitSaladBtn.setOnClickListener {
                priceItemSalad.text = "50"
                typeSaladText.text= "صغير"
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
                priceItemSalad.text = "90"
                typeSaladText.text= "كبير"
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
                if(typeSaladText.text.toString()=="صغير"){
                    val x = numTextSalad.text.toString().toInt()
                    val y = priceItemSalad.text.toString().toInt()
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", typeSaladText.text.toString())
                    intent.putExtra("priceItem", y.toString())
                    intent.putExtra("picItem","https://th.bing.com/th/id/OIP.kCbRZjdGibBMcwFNigsHbwHaE7?rs=1&pid=ImgDetMain")
                    intent.putExtra("quantityItem", binding.numTextSalad.text.toString()+" طبق")
                    intent.putExtra("typeItem","فروت سلاط صغير")
                    startActivity(intent)
                }
                if(typeSaladText.text.toString()=="كبير"){
                    val x = numTextSalad.text.toString().toInt()
                    val y = priceItemSalad.text.toString().toInt()
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", typeSaladText.text.toString())
                    intent.putExtra("priceItem", y.toString())
                    intent.putExtra("picItem", "https://th.bing.com/th/id/OIP.sKgT2tPPF5YHWDVTa1mw-AHaE8?rs=1&pid=ImgDetMain")
                    intent.putExtra("quantityItem", numTextSalad.text.toString()+" طبق")
                    intent.putExtra("typeItem","فروت سلاط كبير")
                    startActivity(intent)
                }
            }


        }
    }

}