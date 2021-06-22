package com.example.shoe.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
//    val hasFinishedHelloSection : MutableLiveData<Boolean> = MutableLiveData(false)
//    val hasFinishedAgeSection   : MutableLiveData<Boolean> = MutableLiveData(false)
//    val hasFinishedTermsSection : MutableLiveData<Boolean> = MutableLiveData(false)
//    val hasFinishedMathSection  : MutableLiveData<Boolean> = MutableLiveData(false)
//    val hasFinishedNameSection  : MutableLiveData<Boolean> = MutableLiveData(false)
    val currentFragmentIndexLiveData : MutableLiveData<Int> = MutableLiveData(0)
    var currentFragmentIndex: Int
        get() = currentFragmentIndexLiveData.value!!
        set(value) {
            currentFragmentIndexLiveData.value = value
        }

}