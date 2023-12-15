package com.example.unclejacks.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unclejacks.R
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.adapters.OnRecyclerViewClick
import com.example.unclejacks.adapters.RecyclerAdapterJuices
import com.example.unclejacks.databinding.FragmentJuicesTabLayoutBinding
import com.example.unclejacks.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class JuicesTabLayoutFragment : Fragment() , OnRecyclerViewClick {

    private lateinit var binding : FragmentJuicesTabLayoutBinding
    private lateinit var adapter : RecyclerAdapterJuices
    private  val roomViewModel: RoomViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_juices_tab_layout,container,false)
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
        roomViewModel.getDataJuiceFromDateBase()

        roomViewModel.readAllDataJuices.observe(viewLifecycleOwner) {
            binding.recyclerViewJuice.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewJuice.adapter = RecyclerAdapterJuices(it,this)
            binding.recyclerViewJuice.setHasFixedSize(true)
            adapter = RecyclerAdapterJuices(it,this)
            adapter.notifyDataSetChanged()


        }

    }


    override fun onClickedRecyclerView(position: Int) {

    }

    override fun onClickedAddCartBtn(position: Int) {

        binding.apply {
            JuiceImage.setBackgroundColor(resources.getColor(R.color.white))

            numTextJuice.text = "1"
            roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) { it ->

                JuiceImage.setImageResource(it[position].itemPic)
                JuiceNameTitle.text = it[position].itemName
                priceItemJuices.text = it[position].itemPrice

                if(binding.numTextJuice.text.toString() == "1")
                {
                    binding.floatingActionButtonMinusJuice.visibility=View.GONE
                }

                floatingActionButtonPlusJuice.setOnClickListener {
                    binding.floatingActionButtonMinusJuice.visibility=View.VISIBLE

                    roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {
                        val xNum = numTextJuice.text.toString()
                        val yNumInt = xNum.toInt()
                        val yNumFinal = yNumInt + 1

                        numTextJuice.text = yNumFinal.toString()

                        val finalPrice = (it[position].itemPrice.toInt()) * yNumFinal
                        priceItemJuices.text = finalPrice.toString()
                    }
                }


            }

            floatingActionButtonMinusJuice.setOnClickListener {
                roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {
                    val xNum = numTextJuice.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numTextJuice.text = yNumFinal.toString()

                    val finalPrice = (it[position].itemPrice.toInt()) * yNumFinal
                    priceItemJuices.text = finalPrice.toString()

                    if(binding.numTextJuice.text.toString() == "1")
                    {
                        binding.floatingActionButtonMinusJuice.visibility=View.GONE
                    }
                }

            }

            addToCartBtnJuice.setOnClickListener {
                roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {
                    val x = binding.numTextJuice.text.toString().toInt()
                    val y = it[position].itemPrice.toString().toInt()
                    val z = x*y

                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", it[position].itemName)
                    intent.putExtra("priceItem", z.toString())
                    intent.putExtra("picItem", it[position].itemPic)
                    intent.putExtra("quantityItem", binding.numTextJuice.text.toString()+" Liters")
                    startActivity(intent)
                }
            }
        }


    }

    override fun onClickedFavoriteBtn(position: Int) {


        roomViewModel.readAllDataJuices.observe(viewLifecycleOwner) {
            val sharedPreferences = activity?.getSharedPreferences("preferences",Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()

            val y = it[position].itemPrice.toString().toInt()
            editor?.putString("nameItem", it[position].itemName)
            editor?.putString("priceItem", y.toString())
            editor?.putInt("picItem", it[position].itemPic)

            editor?.apply()

            Toast.makeText(activity,"Added To Favorite Page, Open it And You can See it.", Toast.LENGTH_LONG).show()

        }
    }



}