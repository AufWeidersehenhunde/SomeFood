package com.example.somefood.RegistrationFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
        ): ViewModel() {
    private val _exp = MutableStateFlow<String>("")
    val exp : MutableStateFlow<String> = _exp

            fun goToBack(){
                router.newRootScreen(Screens.routeToHomeFragment())
            }

    fun register(model: UsersDb){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.registerUser(model)
        }
    }


    fun checkRelative(log: String){
        viewModelScope.launch (Dispatchers.IO){
//            llll.value = repositorySQL.regCheck().toString()
            _exp.value  = repositorySQL.checkLogin(log)
        }
    }
}