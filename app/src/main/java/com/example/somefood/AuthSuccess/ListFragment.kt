package com.example.somefood.AuthSuccess

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.api.R
import com.example.api.databinding.FragmentListBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewListViewModel: ListViewModel by viewModel()
    private val viewBinding: FragmentListBinding by viewBinding()
    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewListViewModel.checkStatus()
        viewBinding.mtrlPickerTextInputDate.text = viewListViewModel.check.value
    }

}