package com.example.somefood.fragmentContainer

import androidx.lifecycle.ViewModel
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router

class ChildViewModel(
    private val router: Router,
    private val repositorySQL: RepositorySQL
) : ViewModel() {

    fun create(it:String){
        router.navigateTo(Screens.routeToProfileFragment(it))
    }

    fun goBack(uuid:String){
        router.backTo(Screens.routeToListFragment(uuid))
    }

    fun routeToFavorite(uuid:String){
        router.navigateTo(Screens.routeToFavoriteFragment(uuid))
    }

}