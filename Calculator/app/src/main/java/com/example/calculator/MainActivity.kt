package com.example.calculator

import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_main.*

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    /**
     * Initializing the variables
     * */
    private var currentNumber: String = ""  // Variable to hold current number.
    private lateinit var button: Button  // Button class object for numerical values.
    private lateinit var buttonOperator: Button  // Button class object for operators.
    private var input1: Double = 0.0 // Variable to hold calculated values.
    private var input2: Double = 0.0 // Variable to hold the second number entered by the user.
    private var numberList = ArrayList<Double>() // List holding numbers on the screen.
    private var operatorList = ArrayList<String>() // List to hold operators.
    private var opCounter = false // Counter used to indicate the position of the operator on the user's screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
    }

    /**
     * This function is responsible to take the values from the user interface and then store the
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
            R.id.buttonback -> { //The last value will be dropped on the execution of this case.
                currentNumber = currentNumber.dropLast(1)
                numberScreen.setText(numberScreen.text.toString().dropLast(1))
                if (opCounter) { //The case will execute if the last value on the screen is an operator.
                    operatorList.removeAt(operatorList.lastIndex)
                    opCounter = false
                    currentNumber = numberList.get(numberList.lastIndex).toString()
                    numberList.removeAt(numberList.lastIndex)
                    numberScreen.setText(currentNumber)


                }

            }
            R.id.buttonclear -> { // Button to clear all the values from the screen and memory.
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
            numberScreen.setText(numberScreen.text.toString() + "0.")
            opCounter = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
            numberScreen.setText(numberScreen.text.toString() + ".")
            opCounter = false
        }
    }

    /**
     * This method performs the operation according to the operators pressed by the user.
     */
    @SuppressLint("SetTextI18n")
    fun operators(view: View) {
        // if the operators pressed is other than '='
        if (view.id != R.id.buttonequal) {
            buttonOperator = view as Button
            Log.d("Operators", "entered operator method")
            // if screen of the user is empty and negative operator is pressed by the user.
            if (numberScreen.text.toString() == "" && view.id == R.id.buttonsub) {
                currentNumber += "-"
                numberScreen.text = currentNumber
            }
            // if the screen of the user is empty and any operator except '-' is pressed.
            else if (numberScreen.text.toString() == "") {
                return
            }
            // if the counter variable for the operator is true that means the last value on the screen is an operator
            // or the operator pressed is positive or negative.
            else if (!opCounter || view.id == R.id.buttonadd || view.id == R.id.buttonsub) {
                // if the second number is entered by the user, then this portion of conditional statement will execute.
                if (currentNumber != "") {
                    numberList.add(currentNumber.toDouble())
                    currentNumber = ""
                    numberScreen.setText(numberScreen.text.toString() + buttonOperator.text.toString())
                    operatorList.add(buttonOperator.text.toString())
                    opCounter = true
                }
                // if the above condition is false then this portion of code will execute.
                else {
                    // Condition to check if the operator list is empty.
                    if (operatorList.lastIndex == -1) {
                        operatorList.add(buttonOperator.text.toString())
                    }
                    // Below condition will be executed to add negative number after '*' and '/' operator is pressed.
                    else if (numberScreen.text.toString().takeLast(1) == "*" && view.id == R.id.buttonsub ||
                            numberScreen.text.toString().takeLast(1) == "/" && view.id == R.id.buttonsub) {
                        currentNumber += "-"
                        numberScreen.text = numberScreen.text.toString() + buttonOperator.text.toString()
                    }
                    // This will add the operator to the operator list.
                    else {
                        operatorList.set(operatorList.lastIndex, buttonOperator.text.toString())
                        opCounter = true
                        numberScreen.text = numberScreen.text.toString().dropLast(1) + buttonOperator.text.toString()
                    }


                }
            }
        }
        // Block of code will execute when '=' is pressed.
        else {
            if (currentNumber != "") {
                Log.d("Operators", "when = is pressed")
                Log.d("Operators", currentNumber)

                numberList.add(currentNumber.toDouble())
                currentNumber = ""
                for (i in 0..operatorList.size - 1) {
                    if (i == 0) {
                        input1 = numberList.get(i)
                        input2 = numberList.get(i + 1)
                        input1 = calculate(input1, input2, operatorList.get(i))

                    } else {
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
     *This method will also return the value after calculation back to operate method.
     */
    fun calculate(in1: Double, in2: Double, op: String): Double {
        when (op) {
            "+" -> {
                Log.d("calc", "add")
                return (in1 + in2)
            }
            "-" -> {
                Log.d("calc", "substract")
                return (in1 - in2)
            }
            "*" -> {
                Log.d("calc", "multiply")
                return (in1 * in2)
            }
            "/" -> {
                Log.d("calc", "divide")
                return (in1 / in2)
            }
        }
        return 0.0
    }
}