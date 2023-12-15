package com.example.unclejacks.fragments

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
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.databinding.FragmentIcecreamTabLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class IcecreamTabLayoutFragment : Fragment() {

    private lateinit var binding : FragmentIcecreamTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_icecream_tab_layout,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinnerName()
        initSpinnerNoOfPieces()


        binding.apply {


            addToCartIcecreamBtn.setOnClickListener {

                if(binding.nameIcecreamText.text.toString().isNotEmpty() && binding.numOfPiecesIcecreamText.text.toString().isNotEmpty()) {
                    val totalPieces = priceItemIcecream.text.toString()

                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", (nameIcecreamText.text.toString()))
                    intent.putExtra("priceItem", totalPieces.toString())
                    intent.putExtra("picItem", R.drawable.icecream)
                    intent.putExtra(
                        "quantityItem",
                        binding.numTextIcecream.text.toString() + " Pieces  " + numOfPiecesIcecreamText.text.toString() + " Spots"
                    )
                    startActivity(intent)
                }
            }



        }
    }


    private fun initSpinnerName(){

        val items = listOf("Select Your Favorite Flavor","Mango","Kiwi","Watermelon","Pineapple","Strawberry","Pomegranate","Chocolate")
        val myAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_dropdown_item,items)

        binding.spinnerIcecreamType.apply {
            adapter=myAdapter
            onItemSelectedListener =object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(items[p2]==items[0]){
                        binding.nameIcecreamText.text=""
                    }else{
                        binding.apply {
                            nameIcecreamText.text=items[p2]


                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }


    private fun initSpinnerNoOfPieces(){

        val items = listOf("Select Your No. Of Spots","1","2","3","4")
        val myAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_dropdown_item,items)

        binding.spinnerNoOfPiecesIcecreamText.apply {
            adapter=myAdapter
            onItemSelectedListener =object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(items[p2]==items[0]){
                        binding.numOfPiecesIcecreamText.text=""
                    }else{
                        binding.apply {
                            numTextIcecream.text = "1"
                            numOfPiecesIcecreamText.text = items[p2]
                            priceItemIcecream.text="7"
                            
                            if (binding.numTextIcecream.text.toString() == "1") {
                                binding.floatingActionButtonMinusIcecream.visibility = View.GONE
                            }

                            floatingActionButtonPlusIcecream.setOnClickListener {
                                binding.floatingActionButtonMinusIcecream.visibility = View.VISIBLE

                                val noOfPiecesString = numTextIcecream.text.toString()
                                val noOfPiecesInt = noOfPiecesString.toInt()
                                val noOfPiecesIntFinal = noOfPiecesInt + 1

                                numTextIcecream.text=noOfPiecesIntFinal.toString()

                                val noOfSpots = items[p2].toInt()
                                val priceTotal = 7 * (noOfSpots * noOfPiecesIntFinal)

                                priceItemIcecream.text = priceTotal.toString()
                            }

                            floatingActionButtonMinusIcecream.setOnClickListener {
                                binding.floatingActionButtonMinusIcecream.visibility = View.VISIBLE

                                val noOfPiecesString = numTextIcecream.text.toString()
                                val noOfPiecesInt = noOfPiecesString.toInt()
                                val noOfPiecesIntFinal = noOfPiecesInt - 1

                                numTextIcecream.text=noOfPiecesIntFinal.toString()


                                val noOfSpots = items[p2].toInt()
                                val priceTotal = 7 * (noOfSpots * noOfPiecesIntFinal)

                                priceItemIcecream.text = priceTotal.toString()


                                if (binding.numTextIcecream.text.toString() == "1") {
                                    binding.floatingActionButtonMinusIcecream.visibility = View.GONE
                                }
                            }

                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }
}