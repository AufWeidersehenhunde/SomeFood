package com.example.somefood.Orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.RecyclerViewItemFavoriteBinding
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.FavoriteFragment.RecyclerViewAdapterFavorite

class RecyclerViewAdapterOrders (): RecyclerView.Adapter<RecyclerViewAdapterOrders.MyViewHolder>() {
    var item: List<FoodDb> = listOf()

    fun set(items: List<FoodDb>) {
        this.item = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: RecyclerViewItemFavoriteBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(food: FoodDb){
            binding.apply {
                nameFood.text = food.name
                Glide.with(imageViewFavorite.context)
                    .load(food.image)
                    .into(imageViewFavorite)
                btnAddToFavourite.setOnClickListener {

                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            RecyclerViewItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }



}