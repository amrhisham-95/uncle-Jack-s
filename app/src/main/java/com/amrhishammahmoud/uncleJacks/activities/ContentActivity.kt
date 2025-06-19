package com.amrhishammahmoud.uncleJacks.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.databinding.ActivityContentBinding
import com.amrhishammahmoud.uncleJacks.fragments.AccountFragmentBottomNav
import com.amrhishammahmoud.uncleJacks.fragments.CategoriesFragmentBottomNav
import com.amrhishammahmoud.uncleJacks.fragments.FavoriteFragmentBottomNav
import com.amrhishammahmoud.uncleJacks.fragments.FruitSaladTabLayoutFragment
import com.amrhishammahmoud.uncleJacks.fragments.FruitsTabLayoutFragment
import com.amrhishammahmoud.uncleJacks.fragments.HomeFragmentBottomNav
import com.amrhishammahmoud.uncleJacks.fragments.IcecreamTabLayoutFragment
import com.amrhishammahmoud.uncleJacks.fragments.JuicesTabLayoutFragment
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal
import com.amrhishammahmoud.uncleJacks.ui.DrawerLocker
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import com.qamar.curvedbottomnaviagtion.gone
import com.qamar.curvedbottomnaviagtion.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentActivity : AppCompatActivity(), DrawerLocker {

    private lateinit var binding: ActivityContentBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content)
        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel

        //ad mob
        val adViewFrameLayout = this.findViewById<FrameLayout>(R.id.adViewFrameLayout)
        //banner ads
        loadBannerAd(this, adViewFrameLayout, AdSize.BANNER, R.string.idAd)

        //curvedBottomNavigation
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                1,
                resources.getString(R.string.home),
                R.drawable.baseline_home_24
            )
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                2,
                resources.getString(R.string.categories),
                R.drawable.baseline_category_24
            )
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                3,
                resources.getString(R.string.favorite),
                R.drawable.baseline_favorite_24
            )
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                4,
                resources.getString(R.string.profile),
                R.drawable.baseline_person_24
            )
        )

        binding.bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> replaceFragment(HomeFragmentBottomNav())
                2 -> replaceFragment(CategoriesFragmentBottomNav())
                3 -> replaceFragment(FavoriteFragmentBottomNav())
                4 -> replaceFragment(AccountFragmentBottomNav())
            }
        }

        replaceFragment(HomeFragmentBottomNav())
        binding.bottomNavigation.show(1)


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

    private fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.add(R.id.frameLayout, fragment)
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
                    roomViewModel.readAllDataFinal.observe(this) {
                        if (it.isEmpty()) {
                            Toast.makeText(this, "أدخل مشترياتك إلى عربة الشراء !", Toast.LENGTH_SHORT)
                                .show()
                        } else {

                            val intent = Intent(this, MyCartActivity::class.java)

                            intent.putExtra("nameItem", "opps")
                            intent.putExtra("priceItem", "0")
                            intent.putExtra(
                                "picItem",
                                "https://safesendsoftware.com/wp-content/uploads/2016/06/Human-Error.jpg"
                            )
                            intent.putExtra("quantityItem", "0")
                            intent.putExtra("typeItem", "opps")



                            val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                                "preferences",
                                Context.MODE_PRIVATE
                            )

                            val total = sharedPreferences.getString("total", "")

                            intent.putExtra("total",total)

                            startActivity(intent)
                        }
                    }

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
                    binding.bottomNavigation.show(4)
                }


                R.id.AllProductsItemNavDrawer -> {
                    replaceFragment(HomeFragmentBottomNav())
                    binding.bottomNavigation.show(1)

                }

                R.id.CategoriesItemNavDrawer -> {
                    replaceFragment(CategoriesFragmentBottomNav())
                    binding.bottomNavigation.show(2)

                }

                R.id.FavoriteItemNavDrawer -> {


                    replaceFragment(FavoriteFragmentBottomNav())
                    binding.bottomNavigation.show(3)

                }

                R.id.MyCartItemNavDrawer -> {
                    roomViewModel.readAllDataFinal.observe(this) {
                        if (it.isEmpty()) {
                            Toast.makeText(this, "أدخل مشترياتك إلى عربة الشراء !", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val intent = Intent(this, MyCartActivity::class.java)
                            intent.putExtra("nameItem", "opps")
                            intent.putExtra("priceItem", "0")
                            intent.putExtra(
                                "picItem",
                                "https://safesendsoftware.com/wp-content/uploads/2016/06/Human-Error.jpg"
                            )
                            intent.putExtra("quantityItem", "0")
                            intent.putExtra("typeItem", "opps")

                            startActivity(intent)
                        }
                    }
                }


                R.id.signOut -> {
                    Toast.makeText(
                        this,
                        "لو أردت الحصول على خدمتنا سجل دخول مرة أخرى",
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
            toolbarContent.title = ""
            toolbarContent.setTitleTextColor(Color.WHITE)
        }

    }


    fun View.gone() {
        visibility = View.GONE
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //Execute your code here
        startActivity(Intent(this, ContentActivity::class.java))
    }


    private fun loadBannerAd(
        context: Context,
        adViewFrameLayout: FrameLayout,
        adSize: AdSize,
        adUnitId: Int
    ) {
        // Create a new ad view.
        val adView = AdView(context)
        adView.setAdSize(adSize)
        adView.adUnitId = context.getString(adUnitId)
        adViewFrameLayout.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                super.onAdFailedToLoad(adError)

                adViewFrameLayout.gone()
                adView.loadAd(adRequest)
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                adViewFrameLayout.visible()
            }
        }
    }


}



