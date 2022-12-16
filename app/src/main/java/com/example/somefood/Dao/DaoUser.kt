package com.example.somefood.Dao

import android.os.Parcelable.Creator
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {

    @Insert
    fun registerUser(usersDb: UsersDb)

    @Query("SELECT login FROM users WHERE login =:log")
    suspend fun checkLogin(log: String): String

    @Query("SELECT* FROM users WHERE login =:log AND password=:pass")
    suspend fun checkAccount(log: String, pass: String): UsersDb

    @Query("UPDATE users SET isCreator = 1 WHERE login=:log")
    fun registerCreator(log: String)

    @Query("UPDATE users SET isCreator = 0 WHERE login=:log")
    fun registerCreator0(log: String)

    @Query("SELECT*FROM users WHERE isCreator=:log")
    fun checkAn(log: String): String
}