package com.example.somefood.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.Flow

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
    fun checkStatus(UUID:String): UsersDb?

    @Insert
    fun insertFoods(list: List<FoodDb>)

    @Query("UPDATE food SET isFavorite = 1 WHERE uuid =:uuid ")
    suspend fun putInFavorite(uuid: String)

    @Query("UPDATE food SET isFavorite = 0 WHERE uuid =:uuid ")
    suspend fun delInFavorite(uuid: String)

    @Query("SELECT*FROM users WHERE uuid=:uuid")
    fun takeProfileInfo(uuid:String):Flow<UsersDb>


    //profileinfo dao
    @Query("UPDATE users SET name =:name WHERE uuid=:uuid")
    suspend fun insertName(name:String, uuid:String)

    @Query("UPDATE users SET description =:des WHERE uuid=:uuid")
    suspend fun insertDescription(des:String, uuid:String)

    @Query("UPDATE users SET address =:address WHERE uuid=:uuid")
    suspend fun insertAddress(address:String, uuid:String)

    // favorite
}