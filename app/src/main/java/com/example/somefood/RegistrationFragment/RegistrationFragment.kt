package com.example.somefood.RegistrationFragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentRegistrationBinding
import com.example.somefood.DBandProvider.UsersDb
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private val viewModelRegistrationFragment: RegistrationViewModel by viewModel()
    private val viewBinding: FragmentRegistrationBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()


    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    private fun initView(){
        with(viewBinding){
            btnBack.setOnClickListener {
                it.hideKeyboard()
                viewModelRegistrationFragment.goToBack()
            }
            btnAccept.setOnClickListener {
                val model = UsersDb(login = viewBinding.loginReg.text.toString(), password = viewBinding.passReg.text.toString())
//                viewModelRegistrationFragment.checkRelative(viewBinding.loginReg.text.toString())
//                if(viewModelRegistrationFragment.exp.value == viewBinding.loginReg.text.toString()){
//                    Toast.makeText(context, "Такой пользователь уже зарегестрирован",Toast.LENGTH_SHORT).show()
//                } else{
                    viewModelRegistrationFragment.register(model)
                }
//               println("pro${viewModelRegistrationFragment.llll.value.toString()}")

            }



        }


    }
}