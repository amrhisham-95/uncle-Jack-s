package com.amrhishammahmoud.uncleJacks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrhishammahmoud.uncleJacks.databinding.CustomLayoutRecyclerView2Binding
import com.amrhishammahmoud.uncleJacks.databinding.CustomRecyclerViewLayoutBinding
import com.amrhishammahmoud.uncleJacks.models.Juices
import com.bumptech.glide.Glide


class RecyclerAdapterJuices(private val Data: List<Juices>, private val onRecyclerViewClick : OnRecyclerViewClick) :
    RecyclerView.Adapter<RecyclerAdapterJuices.MyViewHolder>() {


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
        holder.binding.nameitemCardView.text = currentItem.Name
        holder.binding.priceitemCardView.text = currentItem.Price
        holder.binding.typeitemCardView.text = currentItem.Type

        // holder.binding.itemPicCardView.setImageResource(currentItem.itemPic)
        Glide.with(holder.binding.itemPicCardView.context)
            .load(currentItem.Image)
            .into(holder.binding.itemPicCardView)

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