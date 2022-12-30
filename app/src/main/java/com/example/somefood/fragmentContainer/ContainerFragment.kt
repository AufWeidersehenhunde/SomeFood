package com.example.somefood.fragmentContainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentChildBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContainerFragment : Fragment(R.layout.fragment_child) {
    private val viewModelContainer: ContainerViewModel by viewModel()
    private val viewBinding:FragmentChildBinding by viewBinding()
    private val navigatorHolder2 by inject<NavigatorHolder>()
    private val navigator2 by lazy { AppNavigator(requireActivity(), R.id.host_child, childFragmentManager)}


    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = ContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(DATA, data)
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(DATA)
        if (id != null && savedInstanceState == null) {
            viewModelContainer.create(id)
        }

        with(viewBinding) {
            this.bottomNavigationViewHome.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.btnHome -> {
                        if (id != null) {
                            viewModelContainer.goBack(id)
                        }
                        return@setOnItemSelectedListener true
                    }
                    R.id.btnFavorite -> {
                        if (id != null) {
                            viewModelContainer.routeToFavorite(id)
                        }
                        return@setOnItemSelectedListener true
                    }
                    R.id.profile -> {
                        if (id != null) {
                            viewModelContainer.routeToProfile(id)
                        }
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
