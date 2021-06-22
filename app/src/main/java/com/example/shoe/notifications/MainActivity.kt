package com.example.shoe.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    private val FRAGMENT_TAG = "current.fragment.tag.to.display"
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

        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            Log.d("Image", "${mainViewModel.currentFragmentIndex}")
        }

        mainViewModel.currentFragmentIndexLiveData.observe(this, {
            Log.d("MainActivity", "$it")
            onFragmentIndexChange(it!!)
        })
        if (supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) == null)
            mainViewModel.currentFragmentIndexLiveData.value = 0
    }

    private fun onFragmentIndexChange(newIndex: Int) {
        if (newIndex == fragmentsOrder.size) {
            TODO("ended the flow")
        }
        val fragSection = fragmentsOrder[newIndex]
        val fragment = initFragment(fragSection)
        supportFragmentManager.commit {
            println("MainActivity.onFragmentIndexChange")
            replace(R.id.fragmentContainerView, fragment, FRAGMENT_TAG)
        }
    }

    override fun onBackPressed() {
        if (mainViewModel.currentFragmentIndex > 0) {
            mainViewModel.currentFragmentIndex--
        } else {
            super.onBackPressed()
        }
    }

}