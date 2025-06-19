package com.amrhishammahmoud.uncleJacks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrhishammahmoud.uncleJacks.databinding.CustomFavoriteLayoutBinding
import com.amrhishammahmoud.uncleJacks.models.FavoriteProducts
import com.bumptech.glide.Glide


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
        Glide.with(holder.binding.cartImageFavorite.context)
            .load(currentItem.itemPicFavorite)
            .into(holder.binding.cartImageFavorite)


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