package com.example.shoe.notifications.ageSection

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.BaseAbstractFragment
import com.example.shoe.notifications.R
import java.lang.Integer.parseInt
import java.lang.NumberFormatException

class AgeFragment : BaseAbstractFragment() {
    private val viewModel: AgeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ageInput = view.findViewById<EditText>(R.id.ageInput).apply {setText(viewModel.age.value.toString()) }
        val increaseButton = view.findViewById<ImageButton>(R.id.increaseBtn).apply { isActivated=true }
        val decreaseButton = view.findViewById<ImageButton>(R.id.decreaseBtn).apply { isActivated=true }

        viewModel.age.observe(viewLifecycleOwner, { newAge ->
            ageInput.setText(newAge.toString())
            ageInput.setSelection(ageInput.text.length)
            continueButton.isActivated = newAge >= 18
            increaseButton.isEnabled = (newAge < viewModel.maxAgeValue)
            decreaseButton.isEnabled = (newAge > viewModel.minAgeValue)
        })

        increaseButton.setOnClickListener {
            val age = viewModel.age.value!!
            viewModel.age.value = (age + 1)
        }
        decreaseButton.setOnClickListener {
            val age = viewModel.age.value!!
            viewModel.age.value = (age - 1)
        }
        ageInput.addTextChangedListener {
            val age = try{
                parseInt(it.toString())
            }catch (e : NumberFormatException){
                0
            }
            if (age != viewModel.age.value)
                viewModel.age.value = age
        }

        continueButton.setOnClickListener {
            if (viewModel.age.value!! >= 18){
                mainViewModel.currentFragmentIndex++
            } else {
                Toast.makeText(requireContext(), "Children Not allowed! \nThis service is 18+ Only!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}