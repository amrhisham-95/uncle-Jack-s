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
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterFruits
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterJAcksMixes
import com.amrhishammahmoud.uncleJacks.adapters.RecyclerAdapterJuices
import com.amrhishammahmoud.uncleJacks.databinding.FragmentJuicesTabLayoutBinding
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.bumptech.glide.Glide
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.util.concurrent.TimeoutException

@AndroidEntryPoint

class JuicesTabLayoutFragment : Fragment(), OnRecyclerViewClick {

    private lateinit var binding: FragmentJuicesTabLayoutBinding
    private lateinit var adapter: RecyclerAdapterJuices
    private val roomViewModel: RoomViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_juices_tab_layout, container, false)
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

    /******************************************/


    //put the data into recycler view
    @SuppressLint("NotifyDataSetChanged")
    private  fun putDataIntoRecyclerView() {


        roomViewModel.readAllDataJuices.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                roomViewModel.getDataRetrofitViewModelJuices()
            }
        }

        roomViewModel.mutibaleLiveDataJuices.observe(viewLifecycleOwner){
            Log.d("amr", "putDataIntoRecyclerView: $it")
            roomViewModel.addJuicesDataViewModel(it)
        }

        roomViewModel.readAllDataJuices.observe(viewLifecycleOwner){
            binding.recyclerViewJuices.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewJuices.adapter = RecyclerAdapterJuices(it, this@JuicesTabLayoutFragment)
            binding.recyclerViewJuices.setHasFixedSize(true)
            adapter = RecyclerAdapterJuices(it, this@JuicesTabLayoutFragment)
            adapter.notifyDataSetChanged()
        }



    }


    override fun onClickedRecyclerView(position: Int) {

    }

    override fun onClickedAddCartBtn(position: Int) {
        binding.apply {

            recyclerViewJuices.visibility = View.GONE
            btnJuicesContinueShopping.visibility = View.VISIBLE
            JuicesImage.visibility = View.VISIBLE
            JuicesNameTitle.visibility = View.VISIBLE
            addCartLayoutJuices.visibility = View.VISIBLE

            btnJuicesContinueShopping.setOnClickListener {
                recyclerViewJuices.visibility = View.VISIBLE
                btnJuicesContinueShopping.visibility = View.GONE
                JuicesImage.visibility = View.GONE
                JuicesNameTitle.visibility = View.GONE
                addCartLayoutJuices.visibility = View.GONE
            }


            numTextJuices.text = "1"
            roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {

                Glide.with(binding.JuicesImage.context)
                    .load(it[position].Image)
                    .into(binding.JuicesImage)

                JuicesNameTitle.text = it[position].Name
                priceItemJuices.text = it[position].Price

                if (binding.numTextJuices.text.toString() == "1") {
                    binding.floatingActionButtonMinusJuices.visibility = View.GONE
                }
            }
            floatingActionButtonPlusJuices.setOnClickListener {
                binding.floatingActionButtonMinusJuices.visibility = View.VISIBLE

                roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {
                    val xNum = numTextJuices.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt + 1

                    numTextJuices.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItemJuices.text = finalPrice.toString()

                }


            }

            floatingActionButtonMinusJuices.setOnClickListener {
                roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {

                    val xNum = numTextJuices.text.toString()
                    val yNumInt = xNum.toInt()
                    val yNumFinal = yNumInt - 1
                    numTextJuices.text = yNumFinal.toString()

                    val finalPrice = (it[position].Price.toInt()) * yNumFinal
                    priceItemJuices.text = finalPrice.toString()

                    if (binding.numTextJuices.text.toString() == "1") {
                        binding.floatingActionButtonMinusJuices.visibility = View.GONE
                    }
                }

            }



            addToCartBtnJuices.setOnClickListener {
                roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {

                    val x = binding.numTextJuices.text.toString().toInt()
                    val y = it[position].Price.toString().toInt()
                    val z = x * y
                    val intent = Intent(activity, MyCartActivity::class.java)
                    intent.putExtra("nameItem", it[position].Name)
                    intent.putExtra("priceItem", z.toString())
                    intent.putExtra("picItem", it[position].Image)
                    intent.putExtra("quantityItem", binding.numTextJuices.text.toString() + "  كوب")
                    intent.putExtra("typeItem", it[position].Type)

                    startActivity(intent)
                }
            }

        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClickedFavoriteBtn(position: Int) {

        roomViewModel!!.readAllDataJuices.observe(viewLifecycleOwner) {

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
                "تم إضافة المنتج للقائمة المفضلة افتح القائمة لتعرض قائمتك المفضلة",
                Toast.LENGTH_LONG
            ).show()

        }




    }

}