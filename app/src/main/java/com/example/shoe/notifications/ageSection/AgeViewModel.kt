package com.example.shoe.notifications.ageSection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgeViewModel : ViewModel() {
    val minAgeValue: Int = 0
    val maxAgeValue: Int = 120

    private class AgeLiveData(i: Int, val minValue: Int, val maxValue: Int) : MutableLiveData<Int>(i) {
        override fun setValue(value: Int?) {
            super.setValue(value?.coerceIn(minValue, maxValue) ?: 0 )
        }
    }
    val age : MutableLiveData<Int> = AgeLiveData(18, minAgeValue, maxAgeValue)


}