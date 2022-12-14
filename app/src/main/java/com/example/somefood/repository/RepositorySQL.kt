package com.example.somefood.repository

import com.example.somefood.Dao.DaoUser

class RepositorySQL (
    private val User: DaoUser){
   suspend fun checkAuth(log: String?, pass: String?, b: Boolean)  =
       log?.let {
           if (pass != null) {
               User.checkAuth(it,pass,b)
           }else{println("dirka")}
       }


}