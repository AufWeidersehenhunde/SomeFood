package com.example.somefood.AuthAndAuthorize

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router

class AuthAndRegViewModel(
private val router: Router
) : ViewModel() {

   fun routeToReg(){
       router.newRootScreen(Screens.routeToRegistrationFragment())

   }
    fun routeToAuth(){
        router.newRootScreen(Screens.routeToAuthFragment())
    }
}