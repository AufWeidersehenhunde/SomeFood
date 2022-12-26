package com.example.somefood.DBandProvider

import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "users")
data class UsersDb(
    @PrimaryKey var uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo (name = "description") val description:String = "",
    @ColumnInfo (name = "name") val name:String = "Shlyapka",
    @ColumnInfo (name = "address") val address:String = "",
    @ColumnInfo (name = "mark") val mark:Double = 4.0,
    @ColumnInfo(name = "isCreator") val isCreator: Boolean = false
)