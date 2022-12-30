package com.example.somefood.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.FavoriteFoods
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    private val _listFoods = MutableStateFlow<List<FoodDb>>(emptyList())
    val listFoods:MutableStateFlow<List<FoodDb>> = _listFoods
    val list = MutableStateFlow<List<FavoriteFoods>?>(null)


    private fun takeFavorite(){
        viewModelScope.launch {
            repositorySQL.takeFavorite()?.collect{
                list.value = listOf(it)
            }
            }
        }






    init {
        takeFavorite()
    }





}