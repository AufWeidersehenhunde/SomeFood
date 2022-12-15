package com.example.somefood.DBandProvider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "users")
data class UsersDb(
    @PrimaryKey var uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "password") val password: String?
)