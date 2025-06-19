package com.amrhishammahmoud.uncleJacks.activities


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.databinding.ActivityMyCartBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

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
            runBlocking {
                Toast.makeText(this@MyCartActivity,"جاري العودة لعرض منتجاتنا خلال 5 ثانية ... ",Toast.LENGTH_SHORT).show()
                delay(5000)
                finish() // close this activity and return to preview activity (if there is any)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
        setSupportActionBar(binding.toolbarContent)
        binding.apply {
            toolbarContent.title = "عربة الشراء"
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.setDisplayShowHomeEnabled(true);
            toolbarContent.setTitleTextColor(Color.WHITE)
        }

    }

}