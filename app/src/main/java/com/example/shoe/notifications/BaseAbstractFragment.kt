package com.example.shoe.notifications

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

abstract class BaseAbstractFragment : Fragment() {
    protected val mainViewModel: MainViewModel by activityViewModels()
    protected lateinit var continueButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton = view.findViewById(R.id.continue_button)
    }
}