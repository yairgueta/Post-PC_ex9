package com.example.shoe.notifications.mathSection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.R
import java.lang.NumberFormatException

class MathEquationFragment : Fragment() {
    private val viewModel: MathEquationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.math_equation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val continueButton = view.findViewById<Button>(R.id.continue_button).apply {
            isEnabled = viewModel.userAnswer.value != null
            isActivated = true
            setOnClickListener {
                if (viewModel.isUserRight) {
                    // Next
                } else {
                    Toast.makeText(requireContext(), "Wrong answer! Try again!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        view.findViewById<TextView>(R.id.mathematical_problem).text = viewModel.equation
        view.findViewById<EditText>(R.id.answer_editext).apply {
            setText(viewModel.userAnswer.value?.toString() ?: "")

            addTextChangedListener {
                viewModel.userAnswer.value = try {
                    it?.toString()?.toInt()
                }catch (e: NumberFormatException) {
                    null
                }
            }
        }

        viewModel.userAnswer.observe(viewLifecycleOwner, {
            continueButton.isEnabled = it != null
        })
    }
}