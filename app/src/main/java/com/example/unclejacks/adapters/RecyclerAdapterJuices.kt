package com.example.unclejacks.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unclejacks.databinding.CustomRecyclerViewLayoutBinding
import com.example.unclejacks.models.Juices


class RecyclerAdapterJuices(private val Data: List<Juices>, private val onRecyclerViewClick : OnRecyclerViewClick) :
    RecyclerView.Adapter<RecyclerAdapterJuices.MyViewHolder>() {


    class MyViewHolder(var binding: CustomRecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = CustomRecyclerViewLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = Data[position]
        holder.binding.nameitem.text = currentItem.itemName
        holder.binding.priceitem.text = currentItem.itemPrice
        holder.binding.itemPic.setImageResource(currentItem.itemPic)


        holder.binding.customRecyclerView.setOnClickListener {
            onRecyclerViewClick.onClickedRecyclerView(position)
        }

        holder.binding.addCartBtn.setOnClickListener {
            onRecyclerViewClick.onClickedAddCartBtn(position)
        }

        holder.binding.favoriteitem.setOnClickListener {
            onRecyclerViewClick.onClickedFavoriteBtn(position)
        }
    }


    override fun getItemCount(): Int {
        return Data.size
    }
}