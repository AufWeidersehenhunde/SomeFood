package com.example.somefood.profileFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api.R
import com.example.api.databinding.FragmentProfileBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.AuthSuccessForNonCreator.NonCreatorListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val viewBinding: FragmentProfileBinding by viewBinding()
    private val viewModelProfile:ProfileViewModel by viewModel()

    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, data)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileId = arguments?.getString(DATA)
        viewBinding.btnProfileBack.setOnClickListener {
            viewModelProfile.goBack(profileId.toString())
        }
    }



}