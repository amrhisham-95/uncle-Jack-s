package com.amrhishammahmoud.uncleJacks.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrhishammahmoud.uncleJacks.R
import com.amrhishammahmoud.uncleJacks.activities.ContentActivity
import com.amrhishammahmoud.uncleJacks.activities.MapsActivity
import com.amrhishammahmoud.uncleJacks.adapters.FavoriteRecyclerAdapter
import com.amrhishammahmoud.uncleJacks.adapters.OnCartRecyclerAdapterViewClick
import com.amrhishammahmoud.uncleJacks.databinding.FragmentFavoriteBottomNavBinding
import com.amrhishammahmoud.uncleJacks.models.FavoriteProducts
import com.amrhishammahmoud.uncleJacks.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragmentBottomNav : Fragment(), OnCartRecyclerAdapterViewClick {

    private lateinit var binding: FragmentFavoriteBottomNavBinding
    private lateinit var adapter: FavoriteRecyclerAdapter
    private val roomViewModel: RoomViewModel by viewModels()

    private var list: MutableList<FavoriteProducts> = mutableListOf<FavoriteProducts>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_bottom_nav,
            container,
            false
        )

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = ""

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel


        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            "preferences",
            Context.MODE_PRIVATE
        )

        val dataPic = sharedPreferences.getString("picItem", "")
        val dataName = sharedPreferences.getString("nameItem", "")
        val dataPrice = sharedPreferences.getString("priceItem", "")
        val dataType = sharedPreferences.getString("typeItem", "")

        list.add(FavoriteProducts(dataPic!!, dataName!!, dataType!!, dataPrice!!))


        if (dataPic == "https://safesendsoftware.com/wp-content/uploads/2016/06/Human-Error.jpg"
        ) {
            roomViewModel.readAllDataFavorite.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    Toast.makeText(
                        requireActivity(),
                        "أدخل منتجاتك المفضلة أولاً",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    roomViewModel.readAllDataFavorite.observe(viewLifecycleOwner) {
                        binding.favoriteRecyclerView.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        binding.favoriteRecyclerView.adapter = FavoriteRecyclerAdapter(it, this)
                        binding.favoriteRecyclerView.setHasFixedSize(true)
                        adapter = FavoriteRecyclerAdapter(it, this)
                        adapter.notifyDataSetChanged()
                    }

                }
            }
        } else {
            roomViewModel.getAndPutDataFavoriteIntoDateBase(list)

            roomViewModel.readAllDataFavorite.observe(viewLifecycleOwner) {
                binding.favoriteRecyclerView.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.favoriteRecyclerView.adapter = FavoriteRecyclerAdapter(it, this)
                binding.favoriteRecyclerView.setHasFixedSize(true)
                adapter = FavoriteRecyclerAdapter(it, this)
                adapter.notifyDataSetChanged()
            }


        }

        binding.continueShoppingBtnFavorite.setOnClickListener {
            val intent = Intent(requireActivity(), ContentActivity::class.java)
            startActivity(intent)
        }


        binding.deleteBtnFavorite.setOnClickListener {
            roomViewModel.deleteDataFavorite()

            val sharedPreferences =
                activity?.getSharedPreferences("preferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()

            editor?.putString("nameItem", "")
            editor?.putString("priceItem", "0")
            editor?.putString(
                "picItem", "https://safesendsoftware.com/wp-content/uploads/2016/06/Human-Error.jpg"
            )
            editor?.putString("typeItem", "")

            editor?.apply()

        }

    }

    override fun onClickedRecyclerView(position: Int) {
    }

    override fun onClickedAddCartBtn(position: Int) {
        val intent = Intent(requireActivity(), ContentActivity::class.java)
        startActivity(intent)
    }

}