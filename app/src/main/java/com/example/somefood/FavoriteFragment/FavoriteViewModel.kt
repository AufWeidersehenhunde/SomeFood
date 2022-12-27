package com.example.somefood.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.DBandProvider.Orders
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
    private val list = MutableStateFlow<List<String>>(emptyList())


    fun takeFavorite(){
        viewModelScope.launch {
            repositorySQL.takeFavorite().collect{
                it?.idFood?.let { it1 -> repositorySQL.revertToFavorite(it1) }
                println("444$it" )
            }
        }
    }





    init {
        takeFavorite()
    }





}