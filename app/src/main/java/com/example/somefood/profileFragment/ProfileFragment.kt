package com.example.somefood.profileFragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.api.R
import com.example.api.databinding.FragmentProfileBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val viewBinding: FragmentProfileBinding by viewBinding()
    private val viewModelProfile:ProfileViewModel by viewModel()
    private var btnBoolean:Boolean? = null

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
        if (profileId != null) {
            viewModelProfile.takeProfileInfo(profileId)
        }
        with(viewBinding) {
            textInfo.setOnTouchListener { _, event ->
                if (MotionEvent.ACTION_UP == event.action) {
                    textInfo.setText("${textInfo.text}")
                    btnBoolean = true
                    acceptDescription.visibility = View.VISIBLE
                }
                false
            }

            profileNameHeader.setOnTouchListener { _, event ->
                if (MotionEvent.ACTION_UP == event.action){
                    profileNameHeader.setText("${profileNameHeader.text}")
                    btnBoolean = true
                    acceptName.visibility = View.VISIBLE
                }
                false
            }

            addressProfile.setOnTouchListener { _, event ->
                if (MotionEvent.ACTION_UP == event.action) {
                    addressProfile.setText("${addressProfile.text}")
                    btnBoolean = true
                    acceptAddress.visibility = View.VISIBLE
                }
                false
            }

            acceptName.setOnClickListener {
                if (btnBoolean==true) {
                    it.hideKeyboard()
                    textInfo.setText("${viewBinding.profileNameHeader.text}")
                    btnBoolean = false
                    viewModelProfile.setName("${viewBinding.profileNameHeader.text}", profileId.toString())
                    acceptName.visibility = View.INVISIBLE
                }
            }
            acceptAddress.setOnClickListener {
                if (btnBoolean==true) {
                    it.hideKeyboard()
                    addressProfile.setText("${viewBinding.addressProfile.text}")
                    btnBoolean = false
                    viewModelProfile.setAddress(
                        "${viewBinding.addressProfile.text}",
                        profileId.toString()
                    )
                    acceptAddress.visibility = View.INVISIBLE
                }
            }

            acceptDescription.setOnClickListener {
                if (btnBoolean==true) {
                    it.hideKeyboard()
                    textInfo.setText("${viewBinding.textInfo.text}")
                    btnBoolean = false
                    viewModelProfile.setDescription(
                        "${viewBinding.textInfo.text}",
                        profileId.toString()
                    )
                    acceptDescription.visibility = View.INVISIBLE
                }
            }

            Glide.with(imageViewProfile.context)
                .load(R.drawable.faceanime)
                .into(imageViewProfile)

            Glide.with(imageViewLastFeedback.context)
                .load(R.drawable.aheg)
                .into(imageViewLastFeedback)
            takeItTwo()
        }
    }

    private fun View.hideKeyboard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            viewBinding.textInfo.focusable = View.FOCUSABLE
        }
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun takeItTwo(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelProfile.profile.filterNotNull().collect {
                    with(viewBinding) {
                        idProfile.text = "${it.uuid}"
                        loginProfile.text = " ${it.login}"
                        profileNameHeader.setText("${it.name}")
                        textInfo.setText("${it.description}")
                        addressProfile.setText("${it.address}")
                        mark.text = "${it.mark}/5"
                    }
                }
            }
        }
    }



}