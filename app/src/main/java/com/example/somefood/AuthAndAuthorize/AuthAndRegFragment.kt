package com.example.somefood.AuthAndAuthorize

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentAuthAndRegBinding
import com.example.api.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthAndRegFragment : Fragment(R.layout.fragment_auth_and_reg) {
    private val viewModelAuth: AuthAndRegViewModel by viewModel()
    private val viewBinding: FragmentAuthAndRegBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    initView()
    }

    private fun initView(){
        with(viewBinding) {
            btnSignIn.setOnClickListener {
               viewModelAuth.routeToAuth()
            }
            btnRegistration.setOnClickListener {
                viewModelAuth.routeToReg()
            }
        }
    }

}