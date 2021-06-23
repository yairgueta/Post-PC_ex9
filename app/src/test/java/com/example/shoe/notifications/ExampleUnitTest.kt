package com.example.shoe.notifications

import androidx.lifecycle.MutableLiveData
import com.example.shoe.notifications.nameSection.NameViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {


    @Test
    fun test_firstNameValidation() {
        val nameViewModel = NameViewModel()
        nameViewModel.firstName = Mockito.mock(MutableLiveData::class.java) as MutableLiveData<String>
        `when`(nameViewModel.firstName.value).thenReturn("as")
        assertFalse(nameViewModel.isFirstNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Re'em")
        assertFalse(nameViewModel.isFirstNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Robot@")
        assertFalse(nameViewModel.isFirstNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Maor")
        assertTrue(nameViewModel.isFirstNameValid)
    }

    @Test
    fun test_lastNameValidation() {
        val nameViewModel = NameViewModel()
        nameViewModel.lastName = Mockito.mock(MutableLiveData::class.java) as MutableLiveData<String>

        `when`(nameViewModel.lastName.value).thenReturn("bn")
        assertFalse(nameViewModel.isLastNameValid)

        `when`(nameViewModel.lastName.value).thenReturn("poi'nm")
        assertFalse(nameViewModel.isLastNameValid)

        `when`(nameViewModel.lastName.value).thenReturn("bibi@")
        assertFalse(nameViewModel.isLastNameValid)

        `when`(nameViewModel.lastName.value).thenReturn("Cohen")
        assertTrue(nameViewModel.isLastNameValid)
    }

    @Test
    fun test_fullNameValidation() {
        val nameViewModel = NameViewModel()
        nameViewModel.firstName = Mockito.mock(MutableLiveData::class.java) as MutableLiveData<String>
        nameViewModel.lastName  = Mockito.mock(MutableLiveData::class.java) as MutableLiveData<String>

        `when`(nameViewModel.firstName.value).thenReturn("as")
        `when`(nameViewModel.lastName.value).thenReturn("sn")
        assertFalse(nameViewModel.isFullNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Rami")
        `when`(nameViewModel.lastName.value).thenReturn("lv")
        assertFalse(nameViewModel.isFullNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Sarit@@")
        `when`(nameViewModel.lastName.value).thenReturn("Haddad")
        assertFalse(nameViewModel.isFullNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Nasrin")
        `when`(nameViewModel.lastName.value).thenReturn("Kadri")
        assertTrue(nameViewModel.isFullNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Shiri")
        `when`(nameViewModel.lastName.value).thenReturn("Maimon")
        assertTrue(nameViewModel.isFullNameValid)

        `when`(nameViewModel.firstName.value).thenReturn("Harry")
        `when`(nameViewModel.lastName.value).thenReturn("Potter12")
        assertFalse(nameViewModel.isFullNameValid)
    }
}