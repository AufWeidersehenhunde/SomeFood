package com.example.somefood.AuthSuccessForNonCreator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.api.R
import com.example.api.databinding.FragmentListBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class NonCreatorListFragment : Fragment(R.layout.fragment_list) {
    private val viewListViewModel: NonCreatorListViewModel by viewModel()
    private val viewBinding: FragmentListBinding by viewBinding()

    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = NonCreatorListFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        check()

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