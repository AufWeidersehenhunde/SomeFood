package com.example.somefood.AuthAndAuthorize

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(
private val router: Router,
private val repositorySQL: RepositorySQL
) : ViewModel() {
    private val _auth = MutableLiveData<UsersDb>()
    val auth : MutableLiveData<UsersDb> = _auth

   fun routeToReg(){
       router.newRootScreen(Screens.routeToRegistrationFragment())

   }

    fun register(model:UsersDb){
        repositorySQL.registerUser(model)
    }

    suspend fun checkAuth(log: String?, pass: String?) {
//        _auth.value = repositorySQL.checkAuth(log,pass)
    }
}