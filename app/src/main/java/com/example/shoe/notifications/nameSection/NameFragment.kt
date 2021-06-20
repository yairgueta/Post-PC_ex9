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
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.R

class NameFragment : Fragment() {

    private val viewModel: NameViewModel by viewModels()
    private lateinit var inputWarning: TextView
    private lateinit var continueButton: Button

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






    }

    private fun onValidInput(isValid: Boolean) {
        inputWarning.visibility = if (isValid) TextView.GONE else TextView.VISIBLE
        continueButton.isActivated = isValid
    }

}