package com.example.somefood.MainActivity

import androidx.lifecycle.ViewModel
import com.example.somefood.Screens
import com.github.terrakok.cicerone.Router

class MainActivityViewModel(
    private val router: Router
) : ViewModel(){
    fun create(){
            router.newRootScreen(Screens.routeToHomeFragment())
    }
}