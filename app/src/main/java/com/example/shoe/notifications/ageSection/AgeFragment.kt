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
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.R
import java.lang.Integer.parseInt
import java.lang.NumberFormatException

class AgeFragment : Fragment() {
    private val viewModel: AgeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ageInput : EditText = view.findViewById(R.id.ageInput)
        val increaseButton = view.findViewById<ImageButton>(R.id.increaseBtn)
        val decreaseButton = view.findViewById<ImageButton>(R.id.decreaseBtn)
        val submitButton = view.findViewById<Button>(R.id.submitBtn)

        viewModel.age.observe(viewLifecycleOwner, { newAge ->
            ageInput.setText(newAge.toString())
            ageInput.setSelection(ageInput.text.length)
            submitButton.isActivated = newAge >= 18
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
        ageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val age = try{
                    parseInt(s.toString())
                }catch (e : NumberFormatException){
                    0
                }
                if (age == viewModel.age.value) return
                viewModel.age.value = age
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        submitButton.setOnClickListener {
            if (viewModel.age.value!! >= 18){

            } else {
                Toast.makeText(requireContext(), "Children Not allowed! \nThis service is 18+ Only!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}