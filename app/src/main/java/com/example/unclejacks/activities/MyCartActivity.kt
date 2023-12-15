package com.example.unclejacks.activities


import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.unclejacks.R
import com.example.unclejacks.databinding.ActivityMapsBinding
import com.example.unclejacks.databinding.ActivityMyCartBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MyCartActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMyCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart)
        binding.lifecycleOwner = this

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val findMenuItems = menuInflater
        findMenuItems.inflate(R.menu.menu_cart_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOutItem -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Firebase.auth.signOut()
            }

        }

        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
        setSupportActionBar(binding.toolbarContent)
        binding.apply {
            toolbarContent.title = "My Cart"
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.setDisplayShowHomeEnabled(true);
            toolbarContent.setTitleTextColor(Color.WHITE)
        }

    }

}