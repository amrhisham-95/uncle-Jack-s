package com.example.unclejacks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unclejacks.databinding.CustomCartLayoutBinding
import com.example.unclejacks.databinding.CustomRecyclerViewLayoutBinding
import com.example.unclejacks.models.CartProductsFinal
import com.example.unclejacks.models.Fruits



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
        holder.binding.cartImage.setImageResource(currentItem.itemPicFinal)
        holder.binding.quantityText.text=currentItem.itemQuantityFinal


        holder.binding.cartCustomLayout.setOnClickListener {
            onRecyclerViewClick.onClickedRecyclerView(position)
        }


    }


    override fun getItemCount(): Int {
        return Data.size
    }
}