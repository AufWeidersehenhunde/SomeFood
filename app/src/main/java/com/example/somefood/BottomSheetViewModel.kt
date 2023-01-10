package com.example.somefood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.DBandProvider.FoodDb
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BottomSheetViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {
    val number = MutableStateFlow<Int?>(1)
    val food = MutableStateFlow<FoodDb?>(null)


    fun minusSome(it:String){
        if (number.value != 0 ) {
            number.value = it.toInt() - 1
        }
        else{
            number.value = 0
        }
    }

    fun plusSome(it:String) {
        number.value = it.toInt()+1
    }

    fun takeFood(it:String) {
        viewModelScope.launch {
            repositorySQL.takeFoodForSheet(it).collect{
                food.value = it
            }
        }
    }

    fun putInOrder(idFood:String, idUser:String){
        viewModelScope.launch {
            repositorySQL.addFoodToOrder(idFood,idUser)
        }
    }
}