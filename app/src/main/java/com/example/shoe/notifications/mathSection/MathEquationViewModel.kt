package com.example.shoe.notifications.mathSection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MathEquationViewModel : ViewModel() {

    private enum class Operator(val c: Char) {
        PLUS('+'), MINUS('-'), MULT('*'), DIV('/')
    }

    private class Equation(l: Int, op: Operator, r: Int) {
        val answer : Int = when (op) {
            Operator.PLUS -> l + r
            Operator.MINUS -> l - r
            Operator.MULT -> l * r
            Operator.DIV -> l / r
        }
        val str: String = "$l${op.c}$r=?"
    }

    val userAnswer: MutableLiveData<Int?> = MutableLiveData(null)
    private val operatorsList: Array<Operator> = Operator.values()
    private var currentEquation: Equation? = null

    val equation: String
        get() {
            println("${userAnswer.value}  $currentEquation")
            if (currentEquation == null) generateRandomEquation()
            return currentEquation!!.str
        }

    private fun generateRandomEquation() {
        var l = Random.nextInt(1,12)
        val r = Random.nextInt(1,11)
        val op = operatorsList.random()

        if (op == Operator.DIV) l *= r

        currentEquation = Equation(l, op, r)
    }

    val isUserRight: Boolean
        get() = currentEquation?.let { it.answer == userAnswer.value } ?: false

}