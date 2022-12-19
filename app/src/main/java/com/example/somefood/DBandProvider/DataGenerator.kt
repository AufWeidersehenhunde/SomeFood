package com.example.somefood.DBandProvider

class DataGenerator {

    companion object {
        fun getFoods(): List<FoodDb>{
            return listOf(
                FoodDb("1", "Noma0n", "sas"),
                FoodDb("1", "Noma1n", "sas"),
                FoodDb("1", "Noma2n", "sas"),
            )
        }
    }

}