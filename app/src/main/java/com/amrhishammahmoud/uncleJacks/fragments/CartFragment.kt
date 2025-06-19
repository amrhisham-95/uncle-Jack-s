package com.amrhishammahmoud.uncleJacks.fragments


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.ContentActivity
import com.amrhishammahmoud.uncleJacks.adapters.CartRecyclerAdapter
import com.amrhishammahmoud.uncleJacks.adapters.OnCartRecyclerAdapterViewClick
import com.amrhishammahmoud.uncleJacks.databinding.FragmentCartBinding
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal
import com.amrhishammahmoud.uncleJacks.models.FinalCartData
import com.amrhishammahmoud.uncleJacks.models.KtorClient
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

@AndroidEntryPoint

class CartFragment : Fragment(), OnCartRecyclerAdapterViewClick, LocationListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartRecyclerAdapter
    private val roomViewModel: RoomViewModel by viewModels()
    private var list: MutableList<CartProductsFinal> = mutableListOf<CartProductsFinal>()
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var timeDate = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        getLatitudeAndLongitudeLocation()

        return binding.root

    }


    private fun showTotalPriceOnCartFragment() {
        roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {

            roomViewModel.getDataRetrofitViewModelServices()
            roomViewModel.mutibaleLiveDataServices.observe(viewLifecycleOwner) {
                roomViewModel.addServicesDataViewModel(it)
            }

            roomViewModel.readAllDataServices.observe(viewLifecycleOwner) {

                binding.apply {
                    if(it.isNotEmpty()){
                        vat.text = it[0].VAT
                        serviceFee.text = it[0].Service
                        deliveryFee.text = it[0].Delivery
                    }

                }
            }

            var total = 0
            var totalFinal = 0
            var array = ArrayList<Int>()

            for (element in it) {
                total = element.itemPriceFinal.toInt()
                array.add(total)
            }

            for (i in it.indices) {
                totalFinal += array[i]
            }
            binding.subtotalPrice.text = totalFinal.toDouble().toString()



            runBlocking {
                delay(300)

                if (binding.subtotalPrice.text.toString()
                        .isNotEmpty() && binding.vat.text.isNotEmpty() && binding.deliveryFee.text.isNotEmpty() && binding.serviceFee.text.isNotEmpty()
                ) {

                    val tot = totalFinal.toDouble() + (binding.vat.text.toString()
                        .toInt() + binding.serviceFee.text.toString()
                        .toInt() + binding.deliveryFee.text.toString().toInt()).toDouble()

                    binding.totalAmount.text = "$tot EGP"


                    //to store data
                    val sharedPreferences =
                        activity?.getSharedPreferences("preferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()

                    editor?.putString("total", binding.totalAmount.text.toString())

                    editor?.apply()


                    binding.payBtn.setOnClickListener {

                        findNavController().navigate(
                            CartFragmentDirections.actionCartFragmentToCheckoutFragment(
                                totalFinal,
                                tot.toInt()
                            )
                        )

                }
                }else{
                    val tot = totalFinal.toDouble() + 15.0

                    binding.totalAmount.text = "$tot EGP"


                    //to store data
                    val sharedPreferences =
                        activity?.getSharedPreferences("preferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()

                    editor?.putString("total", binding.totalAmount.text.toString())

                    editor?.apply()


                    binding.payBtn.setOnClickListener {

                        findNavController().navigate(
                            CartFragmentDirections.actionCartFragmentToCheckoutFragment(
                                totalFinal,
                                tot.toInt()
                            )
                        )

                    }
                }
            }


        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel


        showTotalPriceOnCartFragment()

        getPersonalData()


        binding.fatoraShowBtn.setOnClickListener {
            if (binding.editTextNamePerson.text.isNotEmpty() && binding.editTextPhoneNumber.text.isNotEmpty() && binding.editTextTextEmailAddress.text.isNotEmpty()) {

                binding.apply {
                    fatoraShowBtn.visibility = View.GONE
                    editTextNamePerson.visibility = View.GONE
                    editTextPhoneNumber.visibility = View.GONE
                    editTextTextEmailAddress.visibility = View.GONE
                    subtotalPrice.visibility = View.VISIBLE
                    deliveryFee.visibility = View.VISIBLE
                    serviceFee.visibility = View.VISIBLE
                    vat.visibility = View.VISIBLE
                    totalAmount.visibility = View.VISIBLE
                    cartRecyclerView.visibility = View.VISIBLE
                    payBtn.visibility = View.VISIBLE
                    continueShoppingBtn.visibility = View.VISIBLE
                    textView10.visibility = View.VISIBLE
                    textView14.visibility = View.VISIBLE
                    textView16.visibility = View.VISIBLE
                    textView17.visibility = View.VISIBLE
                    textView18.visibility = View.VISIBLE
                    textView19.visibility = View.VISIBLE
                    deleteCartBtn.visibility = View.VISIBLE
                }

                savePersonalData(
                    binding.editTextPhoneNumber.text.toString(),
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextNamePerson.text.toString()
                )


                val intent = requireActivity().intent
                val dataPic = intent.getStringExtra("picItem")
                val dataName = intent.getStringExtra("nameItem")
                val dataPrice = intent.getStringExtra("priceItem")
                val dataQuantity = intent.getStringExtra("quantityItem")
                val dataType = intent.getStringExtra("typeItem")


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val year = LocalDateTime.now().year
                    val month = LocalDateTime.now().month
                    val day = LocalDateTime.now().dayOfMonth

                    val date = "$day-$month-$year"

                    val hour = LocalDateTime.now().hour
                    val minute = LocalDateTime.now().minute
                    val myLdt = LocalDateTime.of(year, month, day, hour, minute)
                    timeDate =
                        "(${myLdt.dayOfMonth}-${myLdt.monthValue}-${myLdt.year}) - (${myLdt.hour}:${myLdt.minute})"
                } else {
                    TODO("VERSION.SDK_INT < O")
                }


            list.add(
                CartProductsFinal(
                    dataPic!!,
                    dataName!!,
                    dataPrice!!,
                    dataType!!,
                    dataQuantity!!,
                    timeDate,
                    binding.editTextNamePerson.text.toString(),
                    binding.editTextPhoneNumber.text.toString(),
                    binding.editTextTextEmailAddress.text.toString()
                )
            )


                if (dataPic == "https://safesendsoftware.com/wp-content/uploads/2016/06/Human-Error.jpg") {
                    binding.totalAmount.text = intent.getStringExtra("total")

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
                        list
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

            }
        }


        binding.continueShoppingBtn.setOnClickListener {
            val intent = Intent(activity, ContentActivity::class.java)
            startActivity(intent)
        }


        binding.deleteCartBtn.setOnClickListener {
            roomViewModel.deleteData()
        }

    }

    override fun onClickedRecyclerView(position: Int) {
    }

    override fun onClickedAddCartBtn(position: Int) {

    }

    //to return back when press arrow inside toolbar
    override fun onDetach() {
        super.onDetach()
        startActivity(Intent(activity, ContentActivity::class.java))
    }

    private fun getLatitudeAndLongitudeLocation() {
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        } else {
            locationManager =
                activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
    }


    private fun savePersonalData(x : String,y : String , z :String){
        val shared: SharedPreferences =
            requireContext().getSharedPreferences("cartData", Context.MODE_PRIVATE)
        val editor = shared.edit()
        editor.putString("number", x)
        editor.putString("email", y)
        editor.putString("name",z)
        editor.apply()
    }


    private fun getPersonalData(){
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("cartData", Context.MODE_PRIVATE)
        val phoneNum = sharedPreferences.getString("number", "")
        val email = sharedPreferences.getString("email", "")
        val name = sharedPreferences.getString("name", "")

        binding.editTextPhoneNumber.setText(phoneNum)
        binding.editTextTextEmailAddress.setText(email)
        binding.editTextNamePerson.setText(name)
    }
}