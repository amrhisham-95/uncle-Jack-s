package com.amrhishammahmoud.uncleJacks.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.MyCartActivity
import com.amrhishammahmoud.uncleJacks.adapters.OnRecyclerViewClick
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterFruits
import com.amrhishammahmoud.uncleJacks.databinding.FragmentFruitsTabLayoutBinding
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.coroutineContext


@AndroidEntryPoint

class FruitsTabLayoutFragment : Fragment(), OnRecyclerViewClick {

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


        roomViewModel.readAllDataFruits.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                roomViewModel.getDataRetrofitViewModelFruits()
            }
        }



        roomViewModel.mutibaleLiveDataFruits.observe(viewLifecycleOwner) {
            Log.d("amr", "putDataIntoRecyclerView: $it")
            roomViewModel.addFruitsDataViewModel(it)
        }

        roomViewModel.readAllDataFruits.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = RecyclerAdapterFruits(it, this@FruitsTabLayoutFragment)
            binding.recyclerView.setHasFixedSize(true)
            adapter = RecyclerAdapterFruits(it, this@FruitsTabLayoutFragment)
            adapter.notifyDataSetChanged()
        }


    }


    override fun onClickedRecyclerView(position: Int) {

    }

    override fun onClickedAddCartBtn(position: Int) {
        binding.apply {

            recyclerView.visibility = View.GONE
            btnFruitContinueShopping.visibility = View.VISIBLE
            fruitImage.visibility = View.VISIBLE
            fruitNameTitle.visibility = View.VISIBLE
            addCartLayoutFruit.visibility = View.VISIBLE

            btnFruitContinueShopping.setOnClickListener {
                recyclerView.visibility = View.VISIBLE
                btnFruitContinueShopping.visibility = View.GONE
                fruitImage.visibility = View.GONE
                fruitNameTitle.visibility = View.GONE
                addCartLayoutFruit.visibility = View.GONE
            }


            numText.text = "1"
            roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {

                Glide.with(binding.fruitImage.context)
                    .load(it[position].Image)
                    .into(binding.fruitImage)

                fruitNameTitle.text = it[position].Name
                priceItem.text = it[position].Price

                if (binding.numText.text.toString() == "1") {
                    binding.floatingActionButtonMinus.visibility = View.GONE
                }
            }
            floatingActionButtonPlus.setOnClickListener {
                binding.floatingActionButtonMinus.visibility = View.VISIBLE

                roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {
                    val xNum = numText.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt + 1

                    numText.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItem.text = finalPrice.toString()

                }


            }

            floatingActionButtonMinus.setOnClickListener {
                roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {

                    val xNum = numText.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numText.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItem.text = finalPrice.toString()

                    if (binding.numText.text.toString() == "1") {
                        binding.floatingActionButtonMinus.visibility = View.GONE
                    }
                }

            }



            addToCartBtn.setOnClickListener {
                roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {

                    val x = binding.numText.text.toString().toInt()
                    val y = it[position].Price.toString().toInt()
                    val z = x * y
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", it[position].Name)
                    intent.putExtra("priceItem", z.toString())
                    intent.putExtra("picItem", it[position].Image)
                    intent.putExtra("quantityItem", binding.numText.text.toString() + "  كيلو جرام")
                    intent.putExtra("typeItem", it[position].Type)

                    startActivity(intent)
                }
            }

        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClickedFavoriteBtn(position: Int) {

        roomViewModel.readAllDataFavorite.observe(this) {

            roomViewModel!!.readAllDataFruits.observe(viewLifecycleOwner) {

                val sharedPreferences =
                    activity?.getSharedPreferences("preferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()

                val y = it[position].Price.toInt()
                editor?.putString("nameItem", it[position].Name)
                editor?.putString("priceItem", y.toString())
                editor?.putString("picItem", it[position].Image)
                editor?.putString("typeItem", it[position].Type)

                editor?.apply()

                Toast.makeText(
                    activity,
                    "تم إضافة المنتج لقائمتك المفضلة افتح القائمة ليتم عرضها",
                    Toast.LENGTH_LONG
                ).show()

            }

        }
    }

}
