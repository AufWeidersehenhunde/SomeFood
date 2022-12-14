package com.example.somefood.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {

    @Query("SELECT*FROM users WHERE login =:log and password =:pass and status =:b")
    suspend fun checkAuth(log: String, pass: String, b: Boolean) : Flow<UsersDb>


}