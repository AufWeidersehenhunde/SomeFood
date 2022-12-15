package com.example.somefood.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {

    @Insert
    fun registerUser(usersDb: UsersDb)

    @Query("SELECT*FROM users  ")
     fun checkAuth() : List<UsersDb>

     @Query("SELECT login FROM users WHERE login =:log")
     suspend fun checkLogin(log:String) : String


}