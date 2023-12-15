package com.example.unclejacks.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.unclejacks.R
import com.example.unclejacks.databinding.ActivityContentBinding
import com.example.unclejacks.fragments.AccountFragmentBottomNav
import com.example.unclejacks.fragments.CategoriesFragmentBottomNav
import com.example.unclejacks.fragments.FavoriteFragmentBottomNav
import com.example.unclejacks.fragments.HomeFragmentBottomNav
import com.example.unclejacks.ui.DrawerLocker
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentActivity : AppCompatActivity(), DrawerLocker {

    private lateinit var binding: ActivityContentBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content)
        binding.lifecycleOwner = this


        //bottomNavigation
        replaceFragment(HomeFragmentBottomNav())

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragmentBottomNav())
                }
                R.id.categories -> {
                    replaceFragment(CategoriesFragmentBottomNav())
                }
                R.id.favorite -> {
                    replaceFragment(FavoriteFragmentBottomNav())
                }
                R.id.account -> {
                    replaceFragment(AccountFragmentBottomNav())
                }

                else -> {


                }

            }
            true
        }


        //navigationDrawer
        makeToggleWithDrawerNavigationContentActivity()

        //to make drawer navigation available
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        makeListenerWithNavigationDrawerItems()

    }

    //to open specific fragment in the frame layout inside the activity
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val findMenuItems = menuInflater
        findMenuItems.inflate(R.menu.menu_content_activity, menu)

        /*//to set number on cart custom view in menu
        val item =menu?.findItem(R.id.cartItem)
        val view =item?.actionView
        val numberCart = view?.findViewById<TextView>(R.id.numCartItems)
        numberCart?.text = "20"
*/
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else {
            when (item.itemId) {
                R.id.signOutItem -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Firebase.auth.signOut()
                }


                R.id.cartItem -> {
                    val intent = Intent(this, MyCartActivity::class.java)
                    intent.putExtra("nameItem", "")
                    intent.putExtra("priceItem", "")
                    intent.putExtra("picItem", android.R.drawable.stat_notify_error)
                    intent.putExtra("quantityItem", "")
                    startActivity(intent)
                }

            }
            super.onOptionsItemSelected(item)
        }

    }

    private fun makeListenerWithNavigationDrawerItems() {
        binding.navigationDrawerView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.ProfileItem -> {
                    //to open specific fragment in the frame layout inside the activity
                    replaceFragment(AccountFragmentBottomNav())
                    //to change the selected item in bottomNavigation
                    binding.bottomNavView.selectedItemId = R.id.account
                }


                R.id.AllProductsItemNavDrawer -> {
                    replaceFragment(HomeFragmentBottomNav())
                    binding.bottomNavView.selectedItemId = R.id.home

                }

                R.id.CategoriesItemNavDrawer -> {
                    replaceFragment(CategoriesFragmentBottomNav())
                    binding.bottomNavView.selectedItemId = R.id.categories

                }

                R.id.FavoriteItemNavDrawer -> {
                    replaceFragment(FavoriteFragmentBottomNav())
                    binding.bottomNavView.selectedItemId = R.id.favorite

                }

                R.id.MyCartItemNavDrawer -> {
                    val intent = Intent(this, MyCartActivity::class.java)
                    intent.putExtra("nameItem", "")
                    intent.putExtra("priceItem", "")
                    intent.putExtra("picItem", android.R.drawable.stat_notify_error)
                    intent.putExtra("quantityItem", "")
                    startActivity(intent)
                }


                R.id.signOut -> {
                    Toast.makeText(
                        this,
                        "Sign In Again, If You Want To Get A Service",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Firebase.auth.signOut()
                }

            }
            true
        }
    }

    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    fun getHideToggleButton() {
        binding.toolbarContent.navigationIcon = null
    }

    fun getToggleSync() {
        toggle.syncState()
    }

    private fun makeToggleWithDrawerNavigationContentActivity() {
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout, binding.toolbarContent,
            R.string.open,
            R.string.close
        )

        //to change the color of toggle
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
        setSupportActionBar(binding.toolbarContent)
        binding.apply {
            toolbarContent.title = "Home"
            toolbarContent.setTitleTextColor(Color.WHITE)
        }

    }


}



