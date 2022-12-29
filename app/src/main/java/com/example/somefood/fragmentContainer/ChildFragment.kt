package com.example.somefood.fragmentContainer

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentChildBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChildFragment : Fragment(R.layout.fragment_child) {
    private val viewModelContainer: ChildViewModel by viewModel()
    private var viewBinding:FragmentChildBinding? = null
    private val navigatorHolder2 by inject<NavigatorHolder>()
    private val navigator1 = this.activity?.let { AppNavigator(it, R.id.host_child) }
    private val navigator2 by lazy { AppNavigator(requireActivity(), R.id.host_child, childFragmentManager)}



    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = ChildFragment().apply {
                arguments = Bundle().apply {
                    putString(DATA, data)
                }
        }
    }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(DATA)


        if (id != null) {
            viewModelContainer.create(id)
        }

        with(viewBinding) {
            this?.bottomNavigationViewHome?.setOnItemSelectedListener {
                when (it.itemId) {
                    com.example.api.R.id.btnHome -> {
                        viewModelContainer.goBack(id.toString())
                        return@setOnItemSelectedListener true
                    }
                    com.example.api.R.id.btnFavorite -> {
                        viewModelContainer.routeToFavorite(id.toString())
                        return@setOnItemSelectedListener true
                    }
                    else -> {
                        return@setOnItemSelectedListener false
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        navigatorHolder2.removeNavigator()
            navigatorHolder2.setNavigator(navigator2)
    }
    override fun onPause() {
        navigatorHolder2.removeNavigator()
        super.onPause()
    }

}
