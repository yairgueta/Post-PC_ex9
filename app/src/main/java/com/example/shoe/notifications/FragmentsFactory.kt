package com.example.shoe.notifications

import com.example.shoe.notifications.ageSection.AgeFragment
import com.example.shoe.notifications.mathSection.MathEquationFragment
import com.example.shoe.notifications.nameSection.NameFragment
import com.example.shoe.notifications.termsSection.TermsFragment



enum class FragmentSection {
    AGE(), MATH(), NAME(), TERMS(), HELLO()
}

fun initFragment(fragmentSection: FragmentSection) : BaseAbstractFragment {
    return when (fragmentSection) {
        FragmentSection.AGE -> AgeFragment()
        FragmentSection.MATH -> MathEquationFragment()
        FragmentSection.NAME -> NameFragment()
        FragmentSection.TERMS -> TermsFragment()
        FragmentSection.HELLO -> HelloFragment()
    }
}