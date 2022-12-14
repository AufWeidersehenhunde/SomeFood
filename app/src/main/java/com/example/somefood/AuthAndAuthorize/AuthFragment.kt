package com.example.somefood.AuthAndAuthorize

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val viewModelAuth: AuthViewModel by viewModel()
    private val viewBinding: FragmentAuthBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    initView()


    }
    private fun initView(){
        with(viewBinding) {
            if (btnAuth.visibility == View.VISIBLE){
                
            }
            fun playVisible(){
                login.isInvisible = !isVisible
                password.isInvisible = !isVisible
                btnAuth.isInvisible = !isVisible
                btnSignIn.isVisible = !isVisible
                btnRegistration.isVisible = !isVisible
            }
            fun playInvisible(){
                login.isVisible = !isVisible
                password.isVisible = !isVisible
                btnAuth.isVisible = !isVisible
                btnSignIn.isInvisible = !isVisible
                btnRegistration.isInvisible = !isVisible
            }
            btnSignIn.setOnClickListener {
                    playVisible()
            }
            btnAuth.setOnClickListener {
                //request and response
                   playInvisible()
            }
        }
    }

}