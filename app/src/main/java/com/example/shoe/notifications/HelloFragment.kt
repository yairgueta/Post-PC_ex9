package com.example.shoe.notifications

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class HelloFragment(var onNextBtmClicked : View.OnClickListener? = null) : Fragment(R.layout.hello_fragment) {

    private lateinit var nextBtn : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextBtn = view.findViewById(R.id.next_btm)
        nextBtn.setOnClickListener(onNextBtmClicked)
    }
}