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
    val list3 = MutableStateFlow<List<String>?>(null)


    private fun takeFavorite(){
        viewModelScope.launch {
            repositorySQL.takeFavorite().collect{
                list.value = it
                println("$it")
            }
            }
        }

    private fun takeFavorite2(){
        viewModelScope.launch {
            list.value?.let {
                list3.value = it.map { it.idFood }
            }
        }
    }

    private fun takeFavorite3(){
        viewModelScope.launch{
            list3.value?.let {
                repositorySQL.takeFavorite(it).collect{
                    if (it != null) {
                        _listFoods.value = it
                    }
                    println("proverka$it")
                }
            }
        }
    }






    init {
        takeFavorite3()
        takeFavorite2()
        takeFavorite()
    }





}