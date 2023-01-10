package com.example.somefood.DBandProvider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorites")
data class FavoriteFoods(
    @PrimaryKey var number:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "idFood") val idFood:String = "polska",
    @ColumnInfo(name = "idUser") val idUser:String = "kurwa"
)
