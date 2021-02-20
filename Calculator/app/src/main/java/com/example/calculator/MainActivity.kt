package com.example.calculator
import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_main.*

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlin.coroutines.EmptyCoroutineContext.plus

class MainActivity : AppCompatActivity() {
    /**
     * Initializing the variables
     * */
    private var currentNumber: String = ""
    private var outputNumber: String = ""
    private lateinit var button: Button
    private lateinit var buttonOperator: Button
    private var input1: Double = 0.0
    private var input2: Double = 0.0
    private var numberList = ArrayList<Double>()
    private var operatorList = ArrayList<String>()
    private var opCounter = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
    }

    /**
     * This function is responsible to take the values from the interface and then store the
     * number in variable currentNumber.
     */
    @SuppressLint("SetTextI18n")
    fun inputValues(view: View) {

        button = view as Button
        currentNumber += button.text
        numberScreen.setText(numberScreen.text.toString() + button.text.toString())
        opCounter = false

    }

    /**This method will work when the DEL key is pressed by the user in the application.
     * It works by deleting the last value from the String whenever the button is pressed.
     */
    fun delete(view: View) {
        when (view.id) {
            R.id.buttonback -> {

                currentNumber = currentNumber.dropLast(1)
                numberScreen.setText(numberScreen.text.toString().dropLast(1))

                if (opCounter)
                {
                    operatorList.removeAt(operatorList.lastIndex)
                    opCounter = false
                }

            }
            R.id.buttonclear ->
            {
                currentNumber = ""
                numberScreen.setText("")
                outputScreen.setText("")
                operatorList.clear()
                numberList.clear()
                opCounter = false
            }
        }
    }

    /**
     * This method works when the '.' operator is pressed by the user.
     * This method assigns'0.' if the first input from the user is '.' and also prevents the
     * user from entering  multiple '.' in the String.
     */
    @SuppressLint("SetTextI18n")
    fun deci(view: View) {
        if (currentNumber == "") {
            currentNumber += "0."
            numberScreen.setText(numberScreen.text.toString()+"0.")
            opCounter = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
            numberScreen.setText(numberScreen.text.toString()+ ".")
            opCounter = false
        }
    }
    /**
     * This method performs the operation according to the operators pressed by the user.
     */
    @SuppressLint("SetTextI18n")
    fun operators(view: View)
    {
        /**This block of code will operate only when operator is pressed by the user, except '=' operator.*/
        if (view.id != R.id.buttonequal)
        {
            buttonOperator = view as Button
            /**Condition statement to stop user from entering multiple operators.*/
            if(opCounter == false)
            {
                /**Condition makes the current number to enter the numberList if it is not empty.*/
                if (currentNumber != "")
                {
                    numberList.add(currentNumber.toDouble())
                    Log.d("Operators",currentNumber)
                    currentNumber = ""
                    numberScreen.setText(numberScreen.text.toString() + buttonOperator.text.toString())
                    operatorList.add(buttonOperator.text.toString())
                    opCounter = true
                }
                else
                {
                    /**If currentNumber is not entered.*/
                    numberScreen.text = numberScreen.text.toString() + buttonOperator.text.toString()
                    Log.d("operator",operatorList.lastIndex.toString())
                    if (operatorList.lastIndex == -1)
                    {
                        operatorList.add(buttonOperator.text.toString())
                    }
                    operatorList.set(operatorList.lastIndex, buttonOperator.text.toString())
                    opCounter = true
                }
            }
        }
            else
            {
                /**This block of code will run only when the '=' is pressed.*/
                if (currentNumber != "")
                {
                    numberList.add(currentNumber.toDouble())
                    currentNumber = ""
                    for (i in 0..operatorList.size-1)
                    {
                        if (i == 0)
                        {
                            input1 = numberList.get(i)
                            input2 = numberList.get(i+1)
                            input1 = calculate(input1, input2, operatorList.get(i))
                        }
                        else
                        {
                            input2 = numberList.get(i + 1)
                            input1 = calculate(input1, input2, operatorList.get(i))
                        }
                    }
                    outputScreen.setText(input1.toString())
                    numberList.clear()
                    operatorList.clear()
                    currentNumber = input1.toString()
                    numberScreen.text = currentNumber
                }
            }
    }
    /**
     *The method 'calculate' is responsible to perform the operation on the operators
     *according to the operator pressed by the user.
     *This method will also return teh value after calculation back to operate method.
     */
    fun calculate(in1: Double, in2: Double, op: String): Double {
        when (op) {
            "+" ->
            {
                Log.d("calc","add")
                return (in1 + in2)
            }
            "-" ->
            {
                Log.d("calc","substract")
                return (in1 - in2)
            }
            "*" ->
            {
                Log.d("calc","multiply")
                return (in1 * in2)
            }
            "/" ->
            {
                Log.d("calc","divide")
                return (in1 / in2)
            }
        }
        return 0.0
    }
}