@file:OptIn(ExperimentalSerializationApi::class)

package com.amrhishammahmoud.uncleJacks.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.MapsActivity
import com.amrhishammahmoud.uncleJacks.databinding.FragmentCheckoutBinding
import com.amrhishammahmoud.uncleJacks.models.BillFinal
import com.amrhishammahmoud.uncleJacks.models.FinalCartData
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.qamar.curvedbottomnaviagtion.gone
import com.qamar.curvedbottomnaviagtion.visible
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.time.LocalDateTime
import java.util.concurrent.TimeoutException


@AndroidEntryPoint

class CheckoutFragment : Fragment(), LocationListener {

    private lateinit var binding: FragmentCheckoutBinding
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private val roomViewModel: RoomViewModel by viewModels()
    private var timeDate = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)

        binding.orderBtn.visibility = View.VISIBLE



        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel





        val priceCheckout = arguments?.let { CheckoutFragmentArgs.fromBundle(it).price }
        val priceTotalCheckout = arguments?.let { CheckoutFragmentArgs.fromBundle(it).totalPrice }

        binding.subtotalPriceCheckout.text = priceCheckout?.toDouble().toString()
        binding.totalAmountCheckout.text = priceTotalCheckout?.toDouble().toString()

        roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.backToCart.visibility = View.VISIBLE
                binding.backToCart.setOnClickListener {
                    findNavController().navigate(R.id.action_checkoutFragment_to_cartFragment)
                }
            }
        }

        //to get latitude and longitude for maps Location
        // getCurrentLocation()
        getLatitudeAndLongitudeLocation()

        binding.locationBtn.setOnClickListener {
            if (binding.latitudeText.text.isNotEmpty() && binding.longitudeText.text.isNotEmpty()) {
                val intent = Intent(requireActivity(), MapsActivity::class.java)
                startActivity(intent)
            }
        }


        roomViewModel.getDataRetrofitViewModelServices()
        roomViewModel.mutibaleLiveDataServices.observe(viewLifecycleOwner) {
            roomViewModel.addServicesDataViewModel(it)
        }

        roomViewModel.readAllDataServices.observe(viewLifecycleOwner) {

            binding.apply {
                if (it.isNotEmpty()) {
                    vatCheckout.text = it[0].VAT
                    serviceFeeCheckout.text = it[0].Service
                    deliveryFeeCheckout.text = it[0].Delivery
                }

            }
        }

        roomViewModel.deleteAllBillFinalDetails()

        roomViewModel.readAllDataBillFinal.observe(
            viewLifecycleOwner
        ) {
            binding.orderBtn.setOnClickListener {

                if (binding.totalAmountCheckout.text.isNotEmpty()) {
                    //to post and transfer final data to the server


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

                        try {
                            //order List
                            roomViewModel.readAllDataFinal.observe(viewLifecycleOwner) {
                                for (i in it.indices) {
                                    roomViewModel.postFinalDataCartToServer(it[i])

                                    //information data
                                    if (binding.latitudeText.text.isNotEmpty() && binding.longitudeText.text.isNotEmpty() && binding.totalAmountCheckout.text.isNotEmpty() && binding.subtotalPriceCheckout.text.isNotEmpty() && binding.serviceFeeCheckout.text.isNotEmpty() && binding.deliveryFeeCheckout.text.isNotEmpty() && binding.vatCheckout.text.isNotEmpty()) {

                                        val longitude = binding.longitudeText.text.toString()
                                        val latitude = binding.latitudeText.text.toString()

                                        val totalPrice =
                                            binding.subtotalPriceCheckout.text.toString()
                                        val totalFinalPrice =
                                            binding.totalAmountCheckout.text.toString()

                                        val deliveryFee =
                                            binding.deliveryFeeCheckout.text.toString()
                                        val serviceFee =
                                            binding.serviceFeeCheckout.text.toString()
                                        val vat = binding.vatCheckout.text.toString()

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            val year = LocalDateTime.now().year
                                            val month = LocalDateTime.now().month
                                            val day = LocalDateTime.now().dayOfMonth

                                            val date = "$day-$month-$year"

                                            val hour = LocalDateTime.now().hour
                                            val minute = LocalDateTime.now().minute
                                            val myLdt =
                                                LocalDateTime.of(year, month, day, hour, minute)
                                            timeDate =
                                                "(${myLdt.dayOfMonth}-${myLdt.monthValue}-${myLdt.year}) - (${myLdt.hour}:${myLdt.minute})"
                                        } else {
                                            TODO("VERSION.SDK_INT < O")
                                        }

                                        val order = BillFinal(
                                            1,
                                            it[i].namePerson,
                                            it[i].telephonePerson,
                                            it[i].emailPerson,
                                            totalPrice,
                                            deliveryFee,
                                            serviceFee,
                                            vat,
                                            totalFinalPrice,
                                            longitude,
                                            latitude,
                                            timeDate
                                        )

                                        val x = emptyList<BillFinal>().toMutableList()
                                        x.add(order)
                                        roomViewModel.addBillFinalDataViewModel(x)


                                        roomViewModel.readAllDataBillFinal.observe(
                                            viewLifecycleOwner
                                        ) {
                                            for (i in it.indices) {
                                                roomViewModel.postBillFinalToServer(it[i])
                                            }
                                        }

                                    }
                                }
                            }

                        } catch (e: TimeoutException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        } catch (e: MissingFieldException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        } catch (e: SerializationException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        } catch (e: IOException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        } catch (e: ClientRequestException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        } catch (e: ServerResponseException) {
                            Log.d("amr", "onViewCreated: ${e.message}")
                        }

                    }

                } else {
                    Toast.makeText(activity, "opps!", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(activity, "تم إرسال الطلب لنا ، انتظر اتصال منا", Toast.LENGTH_SHORT)
                    .show()


                binding.backToCart.visibility = View.GONE
                binding.orderBtn.visibility = View.GONE

                Toast.makeText(
                    activity,
                    "لا يمكنك إجراء تعديل أو شراء منتجات أخرى أو إنشاء سلة مشتريات أخرى إلا بعد اتصالنا بك لتأكيد مشترياتك",
                    Toast.LENGTH_LONG
                ).show()

                roomViewModel.deleteData()
            }
        }

    }


    private fun getCurrentLocation() {
        //to get user location
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        task.addOnSuccessListener {
            if (it != null) {
                binding.latitudeText.text = it.latitude.toString()
                binding.longitudeText.text = it.longitude.toString()
            }
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onLocationChanged(location: android.location.Location) {

        binding.latitudeText.text = location.latitude.toString()
        binding.longitudeText.text = location.longitude.toString()

    }


}


