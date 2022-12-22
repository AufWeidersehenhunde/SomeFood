package com.example.somefood.profileFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
): ViewModel() {
    private val _profile = MutableStateFlow<UsersDb?>(null)
    val profile:MutableStateFlow<UsersDb?> = _profile


    fun goBack(uuid:String){
        router.backTo(Screens.routeToListFragment(uuid))
    }

    fun takeProfileInfo(uuid: String){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.takeProfileInfo(uuid).collect{
                _profile.value = it
            }
        }
    }


}