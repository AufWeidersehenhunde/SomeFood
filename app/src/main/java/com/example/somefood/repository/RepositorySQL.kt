package com.example.somefood.repository

import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Dao.DaoUser

class RepositorySQL (
    private val User: DaoUser){

    fun registerUser(usersDb:UsersDb) = User.registerUser(usersDb)

    suspend fun checkLogin(log:String) = User.checkLogin(log)

    suspend fun checkAccount(log: String, pass:String) = User.checkAccount(log, pass)

    fun checkStatus(UUID:String) = User.checkStatus(UUID)

    fun takeIt() = User.takeIt()

    fun takeProfileInfo(uuid: String) = User.takeProfileInfo(uuid)

    suspend fun putInFavorite(uuid:String) = User.putInFavorite(uuid)

    suspend fun delInFavorite(uuid: String) = User.delInFavorite(uuid)


}