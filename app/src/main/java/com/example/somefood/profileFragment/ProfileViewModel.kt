package com.example.somefood.profileFragment

import androidx.lifecycle.ViewModel
import com.example.somefood.Screens
import com.example.somefood.repository.RepositorySQL
import com.github.terrakok.cicerone.Router

class ProfileViewModel (
    private val router: Router,
    private val repositorySQL: RepositorySQL
): ViewModel() {

    fun goBack(uuid:String){
        router.backTo(Screens.routeToListFragment(uuid))
    }


}