package com.example.shoe.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.system.exitProcess

class AfterOnboarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_onboarding)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}