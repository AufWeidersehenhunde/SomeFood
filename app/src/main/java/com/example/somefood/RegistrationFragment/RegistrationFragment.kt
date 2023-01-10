package com.example.somefood.RegistrationFragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentRegistrationBinding
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private val viewModelRegistrationFragment: RegistrationViewModel by viewModel()
    private val viewBinding: FragmentRegistrationBinding by viewBinding()
    private var creatorStatus: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private suspend fun forToast(){
        viewModelRegistrationFragment.regBoolean.collect{
            if(it){
                Toast.makeText(context, "Такой логин уже занят!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView(){
        with(viewBinding){
            btnPerson.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    creatorStatus = true
                    noncreator.visibility = View.INVISIBLE
                    creator.visibility = View.VISIBLE

                } else {
                    creatorStatus = false
                    creator.visibility = View.INVISIBLE
                    noncreator.visibility = View.VISIBLE

                }
            }
            btnBack.setOnClickListener {
                it.hideKeyboard()
                viewModelRegistrationFragment.goToBack()
            }
            btnAccept.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    if (viewBinding.passReg.text.toString() == "") {
                        Toast.makeText(context, "Введите пароль!!!", Toast.LENGTH_SHORT).show()
                    }
                    else if (viewBinding.passReg.text.toString() != viewBinding.secondPassReg.text.toString())
                    {
                        Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val model = UsersDb(
                            login = viewBinding.loginReg.text.toString(),
                            password = viewBinding.passReg.text.toString(),
                            isCreator = creatorStatus
                        )
                        viewModelRegistrationFragment.register(model)
                        forToast()
                    }
                }
            }
        }
    }
}
