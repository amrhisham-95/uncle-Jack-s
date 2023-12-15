package com.example.unclejacks.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unclejacks.databinding.CustomLayoutRecyclerView2Binding
import com.example.unclejacks.databinding.CustomRecyclerViewLayoutBinding
import com.example.unclejacks.models.Fruits


class RecyclerAdapterFruits(private val Data: List<Fruits>, private val onRecyclerViewClick : OnRecyclerViewClick) :
    RecyclerView.Adapter<RecyclerAdapterFruits.MyViewHolder>() {


    class MyViewHolder(var binding: CustomLayoutRecyclerView2Binding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = CustomLayoutRecyclerView2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = Data[position]
        holder.binding.nameitemCardView.text = currentItem.itemName
        holder.binding.priceitemCardView.text = currentItem.itemPrice
        holder.binding.itemPicCardView.setImageResource(currentItem.itemPic)


        holder.binding.customRecyclerView.setOnClickListener {
            onRecyclerViewClick.onClickedRecyclerView(position)
        }

        holder.binding.addCartBtnCardView.setOnClickListener {
            onRecyclerViewClick.onClickedAddCartBtn(position)
        }

        holder.binding.favoriteitemCardView.setOnClickListener {
            onRecyclerViewClick.onClickedFavoriteBtn(position)
        }
    }


    override fun getItemCount(): Int {
        return Data.size
    }
}
