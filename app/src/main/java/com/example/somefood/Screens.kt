package com.example.somefood

import com.example.somefood.AuthAndAuthorize.AuthFragment
import com.example.somefood.RegistrationFragment.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun routeToHomeFragment() = FragmentScreen {AuthFragment()}
    fun routeToRegistrationFragment() = FragmentScreen{RegistrationFragment()}


}