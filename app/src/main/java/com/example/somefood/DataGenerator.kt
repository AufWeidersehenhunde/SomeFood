package com.example.somefood

import com.example.somefood.DBandProvider.FoodDb

class DataGenerator {

    companion object {
        fun getFoods(): List<FoodDb>{
            return listOf(
                FoodDb("1", "Noma0n", "ds"),
                FoodDb("2", "Noma1n", "ds"),
                FoodDb("3", "Noma2n", "ds")
            )
        }
    }

}