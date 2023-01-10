package com.example.somefood.FavoriteFragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.RecyclerViewItemBinding
import com.example.api.databinding.RecyclerViewItemFavoriteBinding
import com.example.somefood.DBandProvider.FoodDb

class RecyclerViewAdapterFavorite(private val delFavorite: (FoodDb) -> Unit): RecyclerView.Adapter<RecyclerViewAdapterFavorite.MyViewHolder>() {
    var item: List<FoodDb> = listOf()

    fun set(items: List<FoodDb>) {
        this.item = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(food: FoodDb,
        delFavorite:(FoodDb) -> Unit){
            binding.apply {
                viewBtnAddToFavourite.setColorFilter(Color.RED)
                name.text = food.name
                Glide.with(imageView.context)
                    .load(food.image)
                    .into(imageView)
                btnAddToFavourite.setOnClickListener {
                    delFavorite(food)
                }

            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position], delFavorite)

    }

    override fun getItemCount(): Int {
        return item.size
    }



}