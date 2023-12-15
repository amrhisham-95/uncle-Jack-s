package com.example.unclejacks.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unclejacks.R
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.adapters.CartRecyclerAdapter
import com.example.unclejacks.adapters.OnCartRecyclerAdapterViewClick
import com.example.unclejacks.databinding.FragmentCartBinding
import com.example.unclejacks.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CartFragment : Fragment() ,OnCartRecyclerAdapterViewClick {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartRecyclerAdapter
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)


       showTotalPriceOnCartFragment()

        return binding.root

    }


    private fun showTotalPriceOnCartFragment() {
        roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {
            var total = 0
            var totalFinal = 0
            var array = ArrayList<Int>()

            for (element in it) {
                total = element.itemPriceFinal.toString().toInt()
                array.add(total)
            }

            for (i in it.indices) {
                totalFinal += array[i]
            }
            binding.subtotalPrice.text = totalFinal.toDouble().toString()

            if (binding.subtotalPrice.text.toString().isNotEmpty()) {
                val tot = totalFinal.toDouble() + 10.0 + 3.0 + 7.0

                binding.totalAmount.text = "$tot EGP"


                binding.payBtn.setOnClickListener {
                    findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment(totalFinal,tot.toInt()))
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel

        val intent = activity!!.intent
        val dataPic = intent.getIntExtra("picItem", 0)
        val dataName = intent.getStringExtra("nameItem")
        val dataPrice = intent.getStringExtra("priceItem")
        val dataQuantity = intent.getStringExtra("quantityItem")

        if (dataPic == android.R.drawable.stat_notify_error) {
            roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {
                binding.cartRecyclerView.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.cartRecyclerView.adapter = CartRecyclerAdapter(it, this)
                binding.cartRecyclerView.setHasFixedSize(true)
                adapter = CartRecyclerAdapter(it, this)
                adapter.notifyDataSetChanged()
            }
        } else {
            roomViewModel.getAndPutDataFinalIntoDateBase(
                dataName!!,
                dataPrice!!,
                dataPic,
                dataQuantity!!
            )

            roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {
                binding.cartRecyclerView.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.cartRecyclerView.adapter = CartRecyclerAdapter(it, this)
                binding.cartRecyclerView.setHasFixedSize(true)
                adapter = CartRecyclerAdapter(it, this)
                adapter.notifyDataSetChanged()
            }
        }


        binding.continueShoppingBtn.setOnClickListener {
            activity!!.finish()
        }


        binding.deleteCartBtn.setOnClickListener {
            roomViewModel.deleteData()
        }

    }


    override fun onClickedRecyclerView(position: Int) {
    }

    override fun onClickedAddCartBtn(position: Int) {

    }




}