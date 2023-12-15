package com.example.unclejacks.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unclejacks.R
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.adapters.OnRecyclerViewClick
import com.example.unclejacks.adapters.RecyclerAdapterFruits
import com.example.unclejacks.databinding.FragmentFruitsTabLayoutBinding
import com.example.unclejacks.models.CartProductsFinal
import com.example.unclejacks.models.Fruits
import com.example.unclejacks.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import wu.seal.jsontokotlin.utils.times

@AndroidEntryPoint

class FruitsTabLayoutFragment : Fragment() , OnRecyclerViewClick {

    private lateinit var binding: FragmentFruitsTabLayoutBinding
    private lateinit var adapter: RecyclerAdapterFruits
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fruits_tab_layout, container, false)

        return binding.root
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel

        //To confirm that internet is available : (if not : app will show the data that saved in room database ) :
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        if (capabilities != null) {

            putDataIntoRecyclerView()

        }

    }

    //put the data into recycler view
    @SuppressLint("NotifyDataSetChanged")
    private fun putDataIntoRecyclerView() {
        roomViewModel.getDataFruitFromDateBase()

        roomViewModel.readAllDataFruits.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = RecyclerAdapterFruits(it, this)
            binding.recyclerView.setHasFixedSize(true)
            adapter = RecyclerAdapterFruits(it, this)
            adapter.notifyDataSetChanged()


        }

    }


    override fun onClickedRecyclerView(position: Int) {

    }

    override fun onClickedAddCartBtn(position: Int) {
        binding.apply {

            recyclerView.visibility=View.GONE
            btnFruitContinueShopping.visibility=View.VISIBLE
            fruitImage.visibility=View.VISIBLE
            fruitNameTitle.visibility=View.VISIBLE
            addCartLayoutFruit.visibility=View.VISIBLE

            btnFruitContinueShopping.setOnClickListener {
                recyclerView.visibility=View.VISIBLE
                btnFruitContinueShopping.visibility=View.GONE
                fruitImage.visibility=View.GONE
                fruitNameTitle.visibility=View.GONE
                addCartLayoutFruit.visibility=View.GONE
            }


            numText.text = "1"
            roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) { it ->

                fruitImage.setImageResource(it[position].itemPic)
                fruitNameTitle.text = it[position].itemName
                priceItem.text = it[position].itemPrice

                if (binding.numText.text.toString() == "1") {
                    binding.floatingActionButtonMinus.visibility = View.GONE
                }

                floatingActionButtonPlus.setOnClickListener {
                    binding.floatingActionButtonMinus.visibility = View.VISIBLE

                    roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {
                        val xNum = numText.text.toString()
                        val yNumInt = xNum.toInt()
                        val yNumFinal = yNumInt + 1

                        numText.text = yNumFinal.toString()

                        val finalPrice = (it[position].itemPrice.toInt()) * yNumFinal
                        priceItem.text = finalPrice.toString()
                    }
                }


            }

            floatingActionButtonMinus.setOnClickListener {
                roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {
                    val xNum = numText.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numText.text = yNumFinal.toString()

                    val finalPrice = (it[position].itemPrice.toInt()) * yNumFinal
                    priceItem.text = finalPrice.toString()

                    if (binding.numText.text.toString() == "1") {
                        binding.floatingActionButtonMinus.visibility = View.GONE
                    }
                }

            }



            addToCartBtn.setOnClickListener {
                roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {
                    val x = binding.numText.text.toString().toInt()
                    val y = it[position].itemPrice.toString().toInt()
                    val z = x * y
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", it[position].itemName)
                    intent.putExtra("priceItem", z.toString())
                    intent.putExtra("picItem", it[position].itemPic)
                    intent.putExtra("quantityItem", binding.numText.text.toString()+" Kilograms")
                    startActivity(intent)
                }
            }

        }
}


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClickedFavoriteBtn(position: Int) {

            roomViewModel.readAllDataFruits.observe(viewLifecycleOwner) {

                val sharedPreferences = activity?.getSharedPreferences("preferences",Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()

                val y = it[position].itemPrice.toString().toInt()
                editor?.putString("nameItem", it[position].itemName)
                editor?.putString("priceItem", y.toString())
                editor?.putInt("picItem", it[position].itemPic)

                editor?.apply()

                Toast.makeText(activity,"Added To Favorite Page, Open it And You can See it.",Toast.LENGTH_LONG).show()

            }
    }



}
