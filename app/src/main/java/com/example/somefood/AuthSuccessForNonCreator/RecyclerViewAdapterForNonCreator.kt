package com.example.somefood.AuthSuccessForNonCreator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.HuinyaBinding
import com.example.api.databinding.RecyclerViewItemBinding
import com.example.somefood.DBandProvider.FoodDb

class RecyclerViewAdapterForNonCreator (private var favorite: (FoodDb) -> Unit, private var  nonfavorite: (FoodDb) -> Unit): RecyclerView.Adapter<RecyclerViewAdapterForNonCreator.MyViewHolder>() {
    var item: List<FoodDb> = listOf()

    fun set(items: List<FoodDb>) {
        this.item = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: HuinyaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(food:FoodDb,
                 nonfavorite:(FoodDb) ->Unit,
        favorite:(FoodDb) -> Unit){
            binding.apply {
                name.text = food.name
                Glide.with(imageView.context)
                    .load(food.image)
                    .into(imageView)
                btnAddToFavourite.setOnClickListener {
                    if (food.isFavorite==true){
                        nonfavorite(food)
                    }else{
                    favorite(food)
                }}
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
            HuinyaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position], nonfavorite, favorite )

    }

    override fun getItemCount(): Int {
        return item.size
    }



}