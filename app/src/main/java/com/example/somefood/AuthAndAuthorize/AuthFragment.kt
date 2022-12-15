package com.example.somefood.AuthAndAuthorize

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
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
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun initView(){
        with(viewBinding) {
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
//                val log = login.text
//                val pass = password.text
//                viewModelAuth.checkAuth(log.toString(),pass.toString())
//                pngFace.setBackground(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_launcher_background) })
                    playVisible()
            }
            btnAuth.setOnClickListener {
                    it.hideKeyboard()
                pngFace.setBackground(context?.let { ContextCompat.getDrawable(it, R.drawable.faceanime) })
                viewModelAuth.auth
                println("2213`12${viewModelAuth.auth}")
                //request and response
                   playInvisible()
            }
            btnRegistration.setOnClickListener {
                viewModelAuth.routeToReg()
            }
        }
    }

}