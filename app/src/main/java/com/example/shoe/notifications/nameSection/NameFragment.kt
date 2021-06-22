package com.example.shoe.notifications.nameSection

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.BaseAbstractFragment
import com.example.shoe.notifications.R
import com.google.android.material.textfield.TextInputEditText

class NameFragment : BaseAbstractFragment() {

    private val viewModel: NameViewModel by activityViewModels()
    private lateinit var inputWarning: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.name_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputWarning = view.findViewById(R.id.input_warning)
        continueButton = view.findViewById(R.id.continue_button)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            inputWarning.text = Html.fromHtml(getString(R.string.name_warning), 0)
        }

        inputWarning.visibility = View.GONE

        view.findViewById<TextInputEditText>(R.id.first_name_input).apply {
            setText(viewModel.firstName.value)
            addTextChangedListener {
                viewModel.firstName.value = it?.toString() ?: ""
            }
        }

        view.findViewById<TextInputEditText>(R.id.last_name_input).apply {
            setText(viewModel.lastName.value)
            addTextChangedListener {
                viewModel.lastName.value = it?.toString() ?: ""
            }
        }

        viewModel.firstName.observe(viewLifecycleOwner, {
            continueButton.isActivated = viewModel.isFullNameValid
        })

        viewModel.lastName.observe(viewLifecycleOwner, {
            continueButton.isActivated = viewModel.isFullNameValid
        })

        continueButton.setOnClickListener {
            if (viewModel.isFullNameValid) {
                mainViewModel.currentFragmentIndex++
            } else {
                inputWarning.visibility = View.VISIBLE
            }
        }
    }
}