package com.example.shoe.notifications.termsSection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TermsViewModel : ViewModel() {
    val hasAgreed : MutableLiveData<Boolean> = MutableLiveData(false)


}