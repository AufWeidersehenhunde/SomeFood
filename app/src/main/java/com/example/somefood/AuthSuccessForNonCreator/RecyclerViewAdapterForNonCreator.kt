package com.example.somefood.AuthSuccessForNonCreator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.RecyclerViewItemBinding
import com.example.somefood.BottomSheetFragment
import com.example.somefood.DBandProvider.FavoriteFoods
import com.example.somefood.DBandProvider.FoodDb

    class RecyclerViewAdapterForNonCreator (private val favorite: (FoodDb) -> Unit, private val bottomSheet:(FoodDb) -> Unit): RecyclerView.Adapter<RecyclerViewAdapterForNonCreator.MyViewHolder>() {
    var item: List<FoodDb> = listOf()

    fun set(items: List<FoodDb>) {
        this.item = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(food:FoodDb,
        favorite:(FoodDb) -> Unit,
        bottomSheet:(FoodDb) -> Unit){
            binding.apply {
                name.text = food.name
                Glide.with(imageView.context)
                    .load(food.image)
                    .into(imageView)
                btnAddToFavourite.setOnClickListener {
                    favorite(food)
                }
                btnAdd.setOnClickListener {
                    bottomSheet(food)
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
        holder.bind(item[position], favorite, bottomSheet)
    }

    override fun getItemCount(): Int {
        return item.size
    }



}