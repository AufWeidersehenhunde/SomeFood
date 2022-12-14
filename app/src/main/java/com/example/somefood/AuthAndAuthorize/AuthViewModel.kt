package com.example.somefood.AuthAndAuthorize

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
    private val _auth = MutableStateFlow(UsersDb(null,null,null,false) )
    val auth : MutableStateFlow<UsersDb> = _auth

   fun routeToReg(){
       router.newRootScreen(Screens.routeToRegistrationFragment())

   }

    suspend fun checkAuth(log: String?, pass: String?, b: Boolean) {
        _auth.value = repositorySQL.checkAuth(log,pass,b) as UsersDb
    }
}