package com.example.somefood

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.databinding.BottomSheetBinding
import com.example.api.databinding.FragmentBottomSheetBinding
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: BottomSheetViewModel
    private val viewBinding: FragmentBottomSheetBinding by viewBinding()


    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.timePicker.setIs24HourView(true)
  
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)

    }

}


