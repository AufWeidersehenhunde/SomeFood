package com.example.somefood.FavoriteFragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.RecyclerViewItemFavoriteBinding
import com.example.somefood.DBandProvider.FoodDb

class RecyclerViewAdapterFavorite: RecyclerView.Adapter<RecyclerViewAdapterFavorite.MyViewHolder>() {
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
                nameFavorite.text = food.name
                Glide.with(imageViewFavorite.context)
                    .load(food.image)
                    .into(imageViewFavorite)
                btnAddToFavourite.setOnClickListener {

                }
                if (food.isFavorite == true) {
                    viewBtnAddToFavourite.setColorFilter(Color.RED)
                } else {
                    viewBtnAddToFavourite.setColorFilter(Color.WHITE)
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