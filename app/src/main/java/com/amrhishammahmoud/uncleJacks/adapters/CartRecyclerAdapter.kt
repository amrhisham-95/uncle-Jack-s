package com.amrhishammahmoud.uncleJacks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrhishammahmoud.uncleJacks.databinding.CustomCartLayoutBinding
import com.amrhishammahmoud.uncleJacks.models.CartProductsFinal
import com.bumptech.glide.Glide


class CartRecyclerAdapter(private val Data: List<CartProductsFinal>, private val onRecyclerViewClick : OnCartRecyclerAdapterViewClick) :
    RecyclerView.Adapter<CartRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(var binding: CustomCartLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = CustomCartLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = Data[position]
        holder.binding.titleName.text = currentItem.itemNameFinal
        holder.binding.priceItem.text = currentItem.itemPriceFinal
        Glide.with(holder.binding.cartImage.context)
            .load(currentItem.itemPicFinal)
            .into(holder.binding.itemPicCardViewCart)
        holder.binding.quantityText.text=currentItem.itemQuantityFinal


        holder.binding.cartCustomLayout.setOnClickListener {
            onRecyclerViewClick.onClickedRecyclerView(position)
        }


    }


    override fun getItemCount(): Int {
        return Data.size
    }
}