package com.example.somefood.DBandProvider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "orders")
data class Orders(
    @PrimaryKey var number:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "idFood") val idFood:String,
    @ColumnInfo(name = "idUser") val idUser:String,
    @ColumnInfo(name = "time") val time:Int?,
    @ColumnInfo(name = "volume") val volume:String?
)
