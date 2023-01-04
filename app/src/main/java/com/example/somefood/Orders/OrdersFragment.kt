package com.example.somefood.Orders

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentOrdersBinding
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListFragment
import com.example.somefood.AuthSuccessForNonCreator.RecyclerViewAdapterForNonCreator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersFragment : Fragment(R.layout.fragment_orders) {
    private val viewBinding:FragmentOrdersBinding by viewBinding()
    private val viewModelOrders: OrdersViewModel by viewModel()
    private var adapterOrders: RecyclerViewAdapterOrders? = null

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
//            observeElement()
            val profileId = arguments?.getString(DATA)
            adapterOrders =
                RecyclerViewAdapterOrders()

            with(viewBinding.recyclerViewOrders) {
                layoutManager = GridLayoutManager(
                    context,
                    2
                )
                adapter = adapterOrders

            }
        }
    }

//    private fun observeElement() {
//        viewModelOrders.listFoods.onEach {
//            adapterOrders?.set(it)
//        }.launchIn(viewLifecycleOwner.lifecycleScope)
//    }


    private fun check() {
        val id = arguments?.getString(DATA)
        viewLifecycleOwner.lifecycleScope.launch {
            if (id != null) {
                viewModelOrders.checkStatus(id)
            }

        }
    }


}