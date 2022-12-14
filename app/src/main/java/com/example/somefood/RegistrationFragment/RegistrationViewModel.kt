package com.example.somefood.RegistrationFragment

import androidx.lifecycle.ViewModel
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentAuthBinding
import com.example.api.databinding.FragmentRegistrationBinding
import com.example.somefood.AuthAndAuthorize.AuthViewModel
import com.example.somefood.Screens
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationViewModel (
    private val router: Router
        ): ViewModel() {

            fun goToBack(){
                router.newRootScreen(Screens.routeToHomeFragment())
            }

}