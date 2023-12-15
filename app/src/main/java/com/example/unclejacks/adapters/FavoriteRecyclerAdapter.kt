package com.example.unclejacks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unclejacks.databinding.CustomFavoriteLayoutBinding
import com.example.unclejacks.models.FavoriteProducts


class FavoriteRecyclerAdapter(private val Data: List<FavoriteProducts>, private val onRecyclerViewClick : OnCartRecyclerAdapterViewClick) :
    RecyclerView.Adapter<FavoriteRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(var binding: CustomFavoriteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = CustomFavoriteLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = Data[position]
        holder.binding.titleNameFavorite.text = currentItem.itemNameFavorite
        holder.binding.priceItemFavorite.text = currentItem.itemPriceFavorite
        holder.binding.cartImageFavorite.setImageResource(currentItem.itemPicFavorite)


        holder.binding.favoriteCustomLayout.setOnClickListener {
            onRecyclerViewClick.onClickedRecyclerView(position)
        }

        holder.binding.floatingActionButtonShopFavorite.setOnClickListener {
            onRecyclerViewClick.onClickedAddCartBtn(position)
        }

    }


    override fun getItemCount(): Int {
        return Data.size
    }
}