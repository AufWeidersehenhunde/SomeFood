package com.example.somefood.AuthFragment

import android.content.Context
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.api.R
import com.example.api.databinding.FragmentAuthBinding
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val viewBinding: FragmentAuthBinding by viewBinding()
    private val viewModelAuth: AuthViewModel by viewModel()
    private var changesCreator: Boolean = true

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private suspend fun forReal() {
        viewModelAuth.auth.filterNotNull().collect {
            if (it) {
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Wrong password or login!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {
        with(viewBinding) {
            btnBackToSplesh.setOnClickListener {
                viewModelAuth.routeToBack()
                it.hideKeyboard()
            }

            btnVhod.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    if (passAuth.text.toString() == "") {
                        Toast.makeText(context, "Введите пароль!!!", Toast.LENGTH_SHORT).show()
                    } else if (loginAuth.text.toString() == "") {
                        Toast.makeText(context, "Введите логин!!!", Toast.LENGTH_SHORT).show()
                    } else {
                        val model = UsersDb(
                            login = loginAuth.text.toString(),
                            password = passAuth.text.toString(),
                            isCreator = changesCreator
                        )
                        println("local$changesCreator")
                        viewModelAuth.authentication(model)

                        forReal()
                    }
                }
            }
        }
    }
}