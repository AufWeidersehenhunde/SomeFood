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
    val profile: MutableStateFlow<UsersDb?> = _profile


    fun setName(name:String, uuid: String){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.insertName(name,uuid)
        }
    }

    fun setDescription(des:String, uuid:String){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.insertDescription(des, uuid)
        }
    }

    fun setAddress(address:String, uuid:String){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.insertAddress(address, uuid)
        }
    }

    fun takeProfileInfo(uuid: String){
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.takeProfileInfo(uuid).collect{
                _profile.value = it
            }
        }
    }


}