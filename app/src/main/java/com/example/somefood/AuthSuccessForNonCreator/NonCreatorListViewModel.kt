package com.example.somefood.AuthSuccessForNonCreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.DBandProvider.UsersDb
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NonCreatorListViewModel(
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    val listFoods = MutableStateFlow<List<FoodDb>>(emptyList())
    private val _user = MutableStateFlow<UsersDb?>(null)

    init {
        observe()
    }


    fun putFoodToFavorite(uuid:String, id:String){
        viewModelScope.launch(Dispatchers.IO) {
            if (repositorySQL.checkFavoriteFood(uuid, id) != null) {
                repositorySQL.deleteFavoriteFood(repositorySQL.checkFavoriteFood(uuid, id)!!)
            } else {
                repositorySQL.addFoodToFavorite(uuid, id)
            }
        }
    }

    fun checkStatus(uuid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = repositorySQL.checkStatus(uuid)
        }
    }

    private fun observe() {
        viewModelScope.launch {
            repositorySQL.takeIt().collect {
                listFoods.value = it
            }
        }
    }
}