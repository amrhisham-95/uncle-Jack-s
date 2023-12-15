package com.example.unclejacks.fragments

import android.content.Context
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
import com.example.unclejacks.R
import com.example.unclejacks.activities.ContentActivity
import com.example.unclejacks.adapters.CartRecyclerAdapter
import com.example.unclejacks.adapters.FavoriteRecyclerAdapter
import com.example.unclejacks.adapters.OnCartRecyclerAdapterViewClick
import com.example.unclejacks.databinding.FragmentFavoriteBottomNavBinding
import com.example.unclejacks.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragmentBottomNav : Fragment(), OnCartRecyclerAdapterViewClick {

    private lateinit var binding :FragmentFavoriteBottomNavBinding
    private lateinit var adapter: FavoriteRecyclerAdapter
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favorite_bottom_nav,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = "Favorite"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.roomViewModel = roomViewModel


        val sharedPreferences : SharedPreferences = context!!.getSharedPreferences("preferences",
            Context.MODE_PRIVATE)

            val dataPic = sharedPreferences.getInt("picItem",1)
            val dataName = sharedPreferences.getString("nameItem","")
            val dataPrice = sharedPreferences.getString("priceItem","")

        if(dataName == ""){
            roomViewModel.readAllDataFavorite.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    Toast.makeText(requireActivity(), "Enter Your Favorite Products", Toast.LENGTH_SHORT).show()
                } else {
                    roomViewModel.getAndPutDataFavoriteIntoDateBase(
                        dataName!!,
                        dataPrice!!,
                        dataPic)

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
        }else {
            roomViewModel.getAndPutDataFavoriteIntoDateBase(
                dataName!!,
                dataPrice!!,
                dataPic
            )

            roomViewModel.readAllDataFavorite.observe(viewLifecycleOwner) {
                binding.favoriteRecyclerView.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.favoriteRecyclerView.adapter = FavoriteRecyclerAdapter(it, this)
                binding.favoriteRecyclerView.setHasFixedSize(true)
                adapter = FavoriteRecyclerAdapter(it, this)
                adapter.notifyDataSetChanged()
            }


        }

        binding.continueShoppingBtnFavorite.setOnClickListener{
            activity!!.finish()
        }


        binding.deleteBtnFavorite.setOnClickListener {
            roomViewModel.deleteDataFavorite()
        }

    }

    override fun onClickedRecyclerView(position: Int) {
    }

    override fun onClickedAddCartBtn(position: Int) {
        activity!!.finish()
    }

}