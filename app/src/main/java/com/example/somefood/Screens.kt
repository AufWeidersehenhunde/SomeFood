package com.example.somefood

import com.example.somefood.AuthAndAuthorize.AuthAndRegFragment
import com.example.somefood.AuthFragment.AuthFragment
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListFragment
import com.example.somefood.FavoriteFragment.FavoriteFragment
import com.example.somefood.RegistrationFragment.RegistrationFragment
import com.example.somefood.fragmentContainer.ChildFragment
import com.example.somefood.profileFragment.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun routeToHomeFragment() = FragmentScreen { AuthAndRegFragment() }
    fun routeToRegistrationFragment() = FragmentScreen { RegistrationFragment() }
    fun routeToAuthFragment() = FragmentScreen { AuthFragment() }
    fun routeToListFragment(uuid: String) =
        FragmentScreen { NonCreatorListFragment.getInstance(uuid) }
    fun proverka() = FragmentScreen{NonCreatorListFragment()}

    fun routeToProfileFragment(uuid: String) = FragmentScreen { ProfileFragment.getInstance(uuid) }
    fun routeToFavoriteFragment(uuid: String) =
        FragmentScreen { FavoriteFragment.getInstance(uuid) }

    fun routeToFragmentContainer(uuid: String) = FragmentScreen { ChildFragment.getInstance(uuid) }
}