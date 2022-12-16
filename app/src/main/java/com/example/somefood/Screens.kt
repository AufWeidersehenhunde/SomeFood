package com.example.somefood

import com.example.somefood.AuthAndAuthorize.AuthAndRegFragment
import com.example.somefood.AuthFragment.AuthFragment
import com.example.somefood.AuthSuccess.ListFragment
import com.example.somefood.RegistrationFragment.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun routeToHomeFragment() = FragmentScreen {AuthAndRegFragment()}
    fun routeToRegistrationFragment() = FragmentScreen{RegistrationFragment()}
    fun routeToHomeFragmentAfterReg(result:Boolean)  = FragmentScreen{AuthAndRegFragment.getInstance(result)}
    fun routeToAuthFragment() = FragmentScreen{AuthFragment()}
    fun routeToListFragment() = FragmentScreen{ListFragment()}
}