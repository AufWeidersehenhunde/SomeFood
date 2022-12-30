package com.example.somefood.FavoriteFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.internal.findRootView
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentFavoriteBinding
import com.example.somefood.AuthSuccessForNonCreator.RecyclerViewAdapterForNonCreator
import com.example.somefood.profileFragment.ProfileFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewBinding: FragmentFavoriteBinding by viewBinding()
    private val viewModelFavorite: FavoriteViewModel by viewModel()
    private var adapterFavorite:RecyclerViewAdapterFavorite? = null


    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = FavoriteFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.adapterFavorite =
            RecyclerViewAdapterFavorite()
        observeElement()
        with(viewBinding.recyclerViewFavorite) {
            layoutManager = GridLayoutManager(
                context,
                2
            )
            adapter = adapterFavorite
        }
    }

    private fun observeElement() {
        viewModelFavorite.listFoods.onEach {
            adapterFavorite?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}