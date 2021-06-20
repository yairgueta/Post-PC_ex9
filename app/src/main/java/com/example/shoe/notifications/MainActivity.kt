package com.example.shoe.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import com.example.shoe.notifications.mathSection.MathEquationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloFragment = HelloFragment().apply {
            onNextBtmClicked = View.OnClickListener {
                Log.d("FRAG", "Clicked")
            }
        }

        if (supportFragmentManager.findFragmentByTag("HIHI") == null) {
            supportFragmentManager.commit {
                add(R.id.fragmentContainerView, MathEquationFragment(), "HIHI")
            }
        }
    }
}