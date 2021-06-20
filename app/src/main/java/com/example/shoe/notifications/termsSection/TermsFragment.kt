package com.example.shoe.notifications.termsSection

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.shoe.notifications.R

class TermsFragment : Fragment() {
    private val viewModel: TermsViewModel by viewModels()
    private lateinit var checkBox: CheckBox
    private lateinit var shakeAnimation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.terms_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTermsOfServiceText(view)

        shakeAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)

        val continueButton = view.findViewById<Button>(R.id.continue_button).apply {
            setOnClickListener {
                if (!viewModel.hasAgreed.value!!) {
                    animateCheckBoxWarning()
                }
            }

        }
        checkBox = view.findViewById<CheckBox>(R.id.checkBox).apply {
            isChecked = viewModel.hasAgreed.value!!

            setOnClickListener {
                viewModel.hasAgreed.value = isChecked
            }
        }

        viewModel.hasAgreed.observe(viewLifecycleOwner, {
            continueButton.isActivated = it
        })
    }

    private fun animateCheckBoxWarning() {
        checkBox.startAnimation(shakeAnimation)

    }

    private fun initTermsOfServiceText(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.findViewById<TextView>(R.id.terms_content_text_view).text =
                Html.fromHtml(getString(R.string.terms_of_service), 0)
        }
    }
}