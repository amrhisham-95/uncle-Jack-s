package com.amrhishammahmoud.uncleJacks.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.MyCartActivity
import com.amrhishammahmoud.uncleJacks.adapters.OnRecyclerViewClick
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterJAcksMixes
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterJuices
import com.amrhishammahmoud.uncleJacks.databinding.FragmentJacksMixesBinding
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.util.concurrent.TimeoutException

@AndroidEntryPoint
class JacksMixesFragment : Fragment() , OnRecyclerViewClick {

    private lateinit var binding : FragmentJacksMixesBinding
    private lateinit var adapter: RecyclerAdapterJAcksMixes
    private val roomViewModel: RoomViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_jacks_mixes,container,false)

        return binding.root
    }

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


    /******************************************/


    //put the data into recycler view
    @SuppressLint("NotifyDataSetChanged")
    private  fun putDataIntoRecyclerView() {





        roomViewModel.readAllDataJacksMixes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                roomViewModel.getDataRetrofitViewModelJacksMixes()
            }
        }


        roomViewModel.mutibaleLiveDataJacksMixes.observe(viewLifecycleOwner){
            Log.d("amr", "putDataIntoRecyclerView: $it")
            roomViewModel.addJacksMixesDataViewModel(it)
        }

        roomViewModel.readAllDataJacksMixes.observe(viewLifecycleOwner){
            binding.recyclerViewMixes.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewMixes.adapter = RecyclerAdapterJAcksMixes(it, this@JacksMixesFragment)
            binding.recyclerViewMixes.setHasFixedSize(true)
            adapter = RecyclerAdapterJAcksMixes(it, this@JacksMixesFragment)
            adapter.notifyDataSetChanged()
        }



    }


    override fun onClickedRecyclerView(position: Int) {

    }

    override fun onClickedAddCartBtn(position: Int) {
        binding.apply {

            recyclerViewMixes.visibility = View.GONE
            btnMixesContinueShopping.visibility = View.VISIBLE
            MixesImage.visibility = View.VISIBLE
            MixesNameTitle.visibility = View.VISIBLE
            addCartLayoutMixes.visibility = View.VISIBLE

            btnMixesContinueShopping.setOnClickListener {
                recyclerViewMixes.visibility = View.VISIBLE
                btnMixesContinueShopping.visibility = View.GONE
                MixesImage.visibility = View.GONE
                MixesNameTitle.visibility = View.GONE
                addCartLayoutMixes.visibility = View.GONE
            }


            numTextMixes.text = "1"
            roomViewModel!!.readAllDataJacksMixes.observe(viewLifecycleOwner) {

                Glide.with(binding.MixesImage.context)
                    .load(it[position].Image)
                    .into(binding.MixesImage)

                MixesNameTitle.text = it[position].Name
                priceItemMixes.text = it[position].Price

                if (binding.numTextMixes.text.toString() == "1") {
                    binding.floatingActionButtonMinusMixes.visibility = View.GONE
                }
            }
            floatingActionButtonPlusMixes.setOnClickListener {
                binding.floatingActionButtonMinusMixes.visibility = View.VISIBLE

                roomViewModel!!.readAllDataJacksMixes.observe(viewLifecycleOwner) {
                    val xNum = numTextMixes.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt + 1

                    numTextMixes.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItemMixes.text = finalPrice.toString()

                }


            }

            floatingActionButtonMinusMixes.setOnClickListener {
                roomViewModel!!.readAllDataJacksMixes.observe(viewLifecycleOwner) {

                    val xNum = numTextMixes.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numTextMixes.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItemMixes.text = finalPrice.toString()

                    if (binding.numTextMixes.text.toString() == "1") {
                        binding.floatingActionButtonMinusMixes.visibility = View.GONE
                    }
                }

            }



            addToCartBtnMixes.setOnClickListener {
                roomViewModel!!.readAllDataJacksMixes.observe(viewLifecycleOwner) {

                    val x = binding.numTextMixes.text.toString().toInt()
                    val y = it[position].Price.toString().toInt()
                    val z = x * y
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", it[position].Name)
                    intent.putExtra("priceItem", z.toString())
                    intent.putExtra("picItem", it[position].Image)
                    intent.putExtra("quantityItem", binding.numTextMixes.text.toString() + "  قطعة")
                    intent.putExtra("typeItem", it[position].Type)

                    startActivity(intent)
                }
            }

        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClickedFavoriteBtn(position: Int) {

        roomViewModel!!.readAllDataJacksMixes.observe(viewLifecycleOwner) {

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
                "تم إضافة المنتج لقائمتك المفضلة افتح القائمة لتعرضها",
                Toast.LENGTH_LONG
            ).show()

        }




    }

}