package com.example.somefood

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.api.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.api.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModelBottom: BottomSheetViewModel by viewModel()
    private val viewBinding: FragmentBottomSheetBinding by viewBinding()


    companion object {
        private const val FOOD = "UUID food"
        private const val USER = "UUID user"
        fun getInstance(data: String, user:String) = BottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(FOOD, data)
                putString(USER, user)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.timePicker.setIs24HourView(true)
        //numberPicker
        val idFood = arguments?.getString(FOOD)
        val idUser = arguments?.getString(USER)
//        viewBinding.timePicker.drawingTime
        if (idFood != null) {
            viewModelBottom.takeFood(idFood)
        }
        takeFoodInfo()
        with(viewBinding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModelBottom.number.filterNotNull().collect {
                    viewBinding.volume.text = it.toString()
                }
            }

            volumeMinus.setOnClickListener {
                viewModelBottom.minusSome(viewBinding.volume.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModelBottom.number.filterNotNull().collect {
                        viewBinding.volume.text = it.toString()
                    }
                }
            }


            volumePlus.setOnClickListener {
                viewModelBottom.plusSome(viewBinding.volume.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModelBottom.number.filterNotNull().collect {
                        viewBinding.volume.text = it.toString()
                    }
                }
            }
            viewBinding.hideSheet.setOnClickListener {
                //
            }

            viewBinding.acceptOrder.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val minute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        viewBinding.timePicker.minute
                    } else {
                        Toast.makeText(context, "Take minute", Toast.LENGTH_SHORT).show()
                    }
                    val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        viewBinding.timePicker.hour
                    } else {
                        Toast.makeText(context, "Take hours", Toast.LENGTH_SHORT).show()
                    }
                    viewModelBottom.number.filterNotNull().collect {
                        val volume = it
                    }
                    if (idUser != null && idFood != null) {
                            viewModelBottom.putInOrder(idFood,idUser)
                    }
                    //bundle
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    private fun takeFoodInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelBottom.food.filterNotNull().collect {
                    with(viewBinding) {
                        Glide.with(imageForOrderBottomSheet.context)
                            .load(it.image)
                            .into(imageForOrderBottomSheet)
                        nameSheetFood.text = "${it.name}"
                    }
                }
            }
        }


    }
}



