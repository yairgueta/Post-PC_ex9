package com.example.shoe.notifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private val SP_KEY = "shoe.notifications.shared.preferences.key"
    private val KEY_SP_IS_FINISHED_ONBOARDING = "key.sp.is.finished.onboarding"
    private lateinit var sharedPreferences: SharedPreferences

    private val fragmentsOrder = arrayOf(
        FragmentSection.HELLO,
        FragmentSection.AGE,
        FragmentSection.TERMS,
        FragmentSection.MATH,
        FragmentSection.NAME
    )

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar).apply { max = fragmentsOrder.size }

        mainViewModel.currentFragmentIndexLiveData.observe(this, {
            onFragmentIndexChange(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(it, true)
            }else{
                progressBar.progress = it
            }
        })

        sharedPreferences = getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(KEY_SP_IS_FINISHED_ONBOARDING, false)) {
            moveToAfterOnboardingActivity()
        }

        mainViewModel.currentFragmentIndexLiveData.value = 0
    }

    private fun onFragmentIndexChange(newIndex: Int) {
        if (newIndex == fragmentsOrder.size) {
            sharedPreferences.edit {
                putBoolean(KEY_SP_IS_FINISHED_ONBOARDING, true)
                commit()
            }
            moveToAfterOnboardingActivity()
        }
        val fragSection = fragmentsOrder[newIndex]
        if (supportFragmentManager.findFragmentByTag(fragSection.name) == null) {
            val fragment = initFragment(fragSection)
            supportFragmentManager.commit {
                setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                replace(R.id.fragmentContainerView, fragment, fragSection.name)
                addToBackStack(null)
            }
        }
    }

    private fun moveToAfterOnboardingActivity() {
        startActivity(Intent(this, AfterOnboarding::class.java))
    }

    override fun onBackPressed() {
        if (mainViewModel.currentFragmentIndex > 0) {
            mainViewModel.currentFragmentIndex--
        }
        super.onBackPressed()
    }

}