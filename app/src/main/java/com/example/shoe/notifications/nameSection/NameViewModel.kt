package com.example.shoe.notifications.nameSection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    private val firstNameRegex: Regex   = Regex("^[a-zA-Z]{3,}$")
    private val lastNameRegex: Regex    = Regex("^[a-zA-Z]{3,}$")
    val firstName: MutableLiveData<String>  = MutableLiveData("")
    val lastName: MutableLiveData<String>   = MutableLiveData("")

    val isFirstNameValid: Boolean
        get() = firstName.value!!.matches(firstNameRegex)

    val isLastNameValid: Boolean
        get() = lastName.value!!.matches(lastNameRegex)

    val isFullNameValid: Boolean
        get() = isFirstNameValid && isLastNameValid
}