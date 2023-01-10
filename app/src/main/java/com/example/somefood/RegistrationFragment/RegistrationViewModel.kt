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

    private var _regBoolean = MutableStateFlow(false)
            var regBoolean : MutableStateFlow<Boolean> = _regBoolean

    fun goToBack(){
        //swipe back button is not active
        router.navigateTo(Screens.routeToHomeFragment())
    }

    fun register(model: UsersDb){
        _regBoolean.value = false
        viewModelScope.launch(Dispatchers.IO) {
            if (repositorySQL.checkLogin(model.login) == model.login){
                _regBoolean.value = true
            } else {
                repositorySQL.registerUser(model)
                router.navigateTo(Screens.routeToFragmentContainer(model.uuid))
            }
        }
    }
}