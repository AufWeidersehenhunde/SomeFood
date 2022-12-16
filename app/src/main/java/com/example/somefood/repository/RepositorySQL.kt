package com.example.somefood.repository

import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Dao.DaoUser

class RepositorySQL (
    private val User: DaoUser){

    fun registerUser(usersDb:UsersDb) = User.registerUser(usersDb)

    suspend fun checkLogin(log:String) = User.checkLogin(log)

    suspend fun checkAccount(log: String, pass:String) = User.checkAccount(log, pass)

    fun registerCreator (log: String) = User.registerCreator(log)

    fun registerCreator0 (log: String) = User.registerCreator0(log)

    fun checkAn(log: String) = User.checkAn(log)
}