package com.example.somefood.DBandProvider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.Dao.DaoUser

@Database(entities = [UsersDb::class, FoodDb::class, Orders::class], version = 1)
abstract class DBprovider : RoomDatabase() {
    abstract fun DaoUser(): DaoUser
}