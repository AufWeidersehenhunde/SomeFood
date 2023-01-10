package com.example.somefood.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.FavoriteFoods
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    private val _listFoods = MutableStateFlow<List<FoodDb>>(emptyList())
    val listFoods:MutableStateFlow<List<FoodDb>> = _listFoods
    val listFavorite = MutableStateFlow<List<FavoriteFoods>?>(null)
    val listIdFoods = MutableStateFlow<List<String>?>(null)


    init {
        takeFavorite()
    }

    fun delFoodInFavorite(uuid:String, id:String){
        viewModelScope.launch(Dispatchers.IO) {
            if (repositorySQL.checkFavoriteFood(uuid, id) != null) {
                repositorySQL.deleteFavoriteFood(repositorySQL.checkFavoriteFood(uuid, id)!!)
            }
        }
    }



    private fun takeFavorite(){
        viewModelScope.launch {
            repositorySQL.takeAllFavorite().collect{
                listFavorite.value = it
                listFavorite.value?.let{
                    listIdFoods.value = it.map { it.idFood }
                }
                listIdFoods.value?.let {
                    repositorySQL.takeFavorite(it).collect {
                        if (it != null) {
                            _listFoods.value = it
                        }
                    }
                }
            }
            }
        }






}