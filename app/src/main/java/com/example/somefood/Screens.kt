package com.example.somefood

import com.example.somefood.AuthAndAuthorize.AuthFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun routeToHomeFragment() = FragmentScreen {AuthFragment()}
}