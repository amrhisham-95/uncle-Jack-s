package com.example.unclejacks.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.unclejacks.R
import com.example.unclejacks.activities.MapsActivity
import com.example.unclejacks.activities.MyCartActivity
import com.example.unclejacks.databinding.FragmentCheckoutBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)


        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val priceCheckout = arguments?.let { CheckoutFragmentArgs.fromBundle(it).price }
        val priceTotalCheckout = arguments?.let { CheckoutFragmentArgs.fromBundle(it).totalPrice }

        binding.subtotalPriceCheckout.text = priceCheckout?.toDouble().toString()
        binding.totalAmountCheckout.text = priceTotalCheckout?.toDouble().toString()

        binding.backToCart.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_cartFragment)
        }

        binding.locationBtn.setOnClickListener {

            //to get latitude and longitude for maps Location
            getCurrentLocation()

            if(binding.latitudeText.text.isNotEmpty() && binding.longitudeText.text.isNotEmpty()){
                val intent = Intent(requireActivity(),MapsActivity::class.java)
                startActivity(intent)
            }

        }

        binding.orderBtn.setOnClickListener {

        }

    }


   private fun getCurrentLocation(){
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
         ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
           return
       }

       task.addOnSuccessListener {
           if(it != null){
               binding.latitudeText.text = it.latitude.toString()
               binding.longitudeText.text = it.longitude.toString()
           }
       }
    }

}


