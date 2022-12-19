package com.example.somefood.DBandProvider

import android.content.ContentProvider
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.somefood.DBandProvider.DataGenerator.Companion.getFoods
import com.example.somefood.Dao.DaoUser
import java.util.concurrent.Executors

@Database(entities = [UsersDb::class, FoodDb::class], version = 1)
abstract class DBprovider : RoomDatabase() {
    abstract fun DaoUser(): DaoUser

}