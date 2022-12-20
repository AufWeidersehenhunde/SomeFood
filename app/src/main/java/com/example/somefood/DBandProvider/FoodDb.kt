package com.example.somefood.DBandProvider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "food")
data class FoodDb(
    @PrimaryKey var uuid:String,
    @ColumnInfo(name = "name") val name: String = "3",
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "isFavorite") val isFavorite:Boolean?
)