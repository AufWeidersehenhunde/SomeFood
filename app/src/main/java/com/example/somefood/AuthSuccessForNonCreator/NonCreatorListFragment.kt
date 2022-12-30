package com.example.somefood.AuthSuccessForNonCreator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.R
import com.example.api.databinding.FragmentListBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class NonCreatorListFragment : Fragment(R.layout.fragment_list) {
    private val viewListViewModel: NonCreatorListViewModel by viewModel()
    private val viewBinding: FragmentListBinding by viewBinding()
    private var adapterHome:RecyclerViewAdapterForNonCreator? = null

    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = NonCreatorListFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
            println("dds2${arguments?.getString(DATA)}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            check()
            observeElement()
            val profileId = arguments?.getString(DATA)
            adapterHome =
                RecyclerViewAdapterForNonCreator({
                    if (profileId != null) {
                        viewListViewModel.putFoodToFavorite(it.uuid, profileId)
                    }
                })

            with(viewBinding.recyclerView) {
                layoutManager = GridLayoutManager(
                    context,
                    2
                )
                adapter = adapterHome

            }
        }
    }


    private fun observeElement() {
        viewListViewModel.listFoods.onEach {
            adapterHome?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun check() {
        val id = arguments?.getString(DATA)
        viewLifecycleOwner.lifecycleScope.launch {
            if (id != null) {
                viewListViewModel.checkStatus(id)
            }

        }
    }

}