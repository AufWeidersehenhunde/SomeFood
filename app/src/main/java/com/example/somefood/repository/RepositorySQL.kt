package com.example.somefood.repository

import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Dao.DaoUser

class RepositorySQL (
    private val User: DaoUser){

    fun registerUser(usersDb:UsersDb) = User.registerUser(usersDb)

     fun regCheck() = User.checkAuth()

    suspend fun checkLogin(log:String) = User.checkLogin(log)

}