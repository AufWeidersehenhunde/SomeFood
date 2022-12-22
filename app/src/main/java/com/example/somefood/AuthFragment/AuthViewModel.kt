package com.example.somefood.AuthFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    private val forId = MutableStateFlow<UsersDb?>(null)
    private val _auth = MutableStateFlow<Boolean?>(null)
    val auth: MutableStateFlow<Boolean?> = _auth


    fun routeToBack() {
        router.backTo(Screens.routeToHomeFragment())
    }

    fun authentication(model: UsersDb) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repositorySQL.checkAccount(model.login, model.password) != null && repositorySQL.checkAccount(model.login, model.password)?.isCreator != true  )  {
                _auth.value = true
                forId.value = repositorySQL.checkAccount(model.login, model.password)
                forId.value?.let { Screens.routeToListFragment(it.uuid) }
                    ?.let { router.navigateTo(it) }
            }
            else{
                _auth.value = false
            }
        }
    }
}