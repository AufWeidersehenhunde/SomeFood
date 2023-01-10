package com.example.somefood.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.somefood.DBandProvider.FavoriteFoods
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.DBandProvider.Orders
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface DaoUser {

    @Insert
    fun registerUser(usersDb: UsersDb)

    @Query("SELECT*FROM food")
    fun takeIt(): Flow<List<FoodDb>>

    @Query("SELECT login FROM users WHERE login =:log")
    suspend fun checkLogin(log: String): String

    @Query("SELECT* FROM users WHERE login =:log AND password=:pass")
    suspend fun checkAccount(log: String, pass: String): UsersDb?

    @Query("SELECT*FROM users WHERE uuid=:UUID")
    fun checkStatus(UUID: String): UsersDb?

    @Insert
    fun insertFoods(list: List<FoodDb>)

    @Query("SELECT*FROM users WHERE uuid=:uuid")
    fun takeProfileInfo(uuid: String): Flow<UsersDb>


    //profile info dao
    @Query("UPDATE users SET name =:name WHERE uuid=:uuid")
    suspend fun insertName(name: String, uuid: String)

    @Query("UPDATE users SET description =:des WHERE uuid=:uuid")
    suspend fun insertDescription(des: String, uuid: String)

    @Query("UPDATE users SET address =:address WHERE uuid=:uuid")
    suspend fun insertAddress(address: String, uuid: String)

    // favorite
    @Insert
    fun addFoodToFavorite(model: FavoriteFoods)

    @Query("SELECT* FROM favorites")
    fun takeAllFavorite(): Flow<List<FavoriteFoods>?>

    @Query("SELECT*FROM food WHERE uuid IN (:it)")
    fun takeFavorite(it: List<String>): Flow<List<FoodDb>?>

    @Query("SELECT* FROM favorites WHERE idFood =:uuid AND idUser=:id")
    suspend fun checkFavoriteFood(uuid: String, id: String): FavoriteFoods?

    @Delete
    suspend fun deleteFavoriteFood(model: FavoriteFoods)

    //sheet

    @Query("SELECT*FROM food WHERE uuid=:it")
    fun takeFoodForSheet(it: String): Flow<FoodDb>

    @Insert
    fun addFoodToOrder(model:Orders)

}