package com.example.somefood.fragmentContainer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentChildBinding
import com.example.somefood.FavoriteFragment.FavoriteFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChildFragment : Fragment(R.layout.fragment_child) {
    private val viewModelContainer: ChildViewModel by viewModel()
    private val viewBinding:FragmentChildBinding by viewBinding()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = this.activity?.let { AppNavigator(it, R.id.host_child) }



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
            bottomNavigationViewHome.setOnItemSelectedListener {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (navigator != null) {
            navigatorHolder.setNavigator(navigator)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigatorHolder.removeNavigator()
    }

}
