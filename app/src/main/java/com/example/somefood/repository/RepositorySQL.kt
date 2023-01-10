package com.example.somefood.repository

import com.example.somefood.DBandProvider.FavoriteFoods
import com.example.somefood.DBandProvider.Orders
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


    // profile info
    suspend fun insertName(name:String, uuid: String) = User.insertName(name, uuid)

    suspend fun insertDescription(des:String, uuid: String) = User.insertDescription(des, uuid)

    suspend fun insertAddress(address:String, uuid: String) = User.insertAddress(address, uuid)

    //favorite
    fun takeAllFavorite() = User.takeAllFavorite()

    fun addFoodToFavorite(uuid:String, id:String) {
        val model = FavoriteFoods(idFood = uuid, idUser = id)
        User.addFoodToFavorite(model)
    }

    fun addFoodToOrder(uuid:String, id:String) {
        val model = Orders(idFood = uuid, idUser = id, time = null)
    }

    suspend fun checkFavoriteFood(uuid: String, id:String) = User.checkFavoriteFood(uuid, id)

    fun takeFavorite(it: List<String>) = User.takeFavorite(it)

    suspend fun deleteFavoriteFood(model:FavoriteFoods) = User.deleteFavoriteFood(model)

}