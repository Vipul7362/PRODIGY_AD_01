
package com.example.calculatorapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculatorapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var editText: TextView
    private var currentOperation = ""
    private var currentValue = "0"
    private var previousValue = "0"
    private var operationStack = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextTextPersonName6)

        val buttons = listOf(
            findViewById<Button>(R.id.b1),
            findViewById<Button>(R.id.button15),
            findViewById<Button>(R.id.button16),
            findViewById<Button>(R.id.button17),
            findViewById<Button>(R.id.button21),
            findViewById<Button>(R.id.button22),
            findViewById<Button>(R.id.button23),
            findViewById<Button>(R.id.button24),
            findViewById<Button>(R.id.button25),
            findViewById<Button>(R.id.button26),
            findViewById<Button>(R.id.button27),
            findViewById<Button>(R.id.button28),
            findViewById<Button>(R.id.button29),
            findViewById<Button>(R.id.button30),
            findViewById<Button>(R.id.button31),
            findViewById<Button>(R.id.button32),
            findViewById<Button>(R.id.button33),
            findViewById<Button>(R.id.button34),
            findViewById<Button>(R.id.button35),
            findViewById<Button>(R.id.button36)
        )

        buttons.forEach { button ->
            button.setOnClickListener { handleButtonPress(button.text.toString()) }
        }
    }

    private fun handleButtonPress(text: String) {
        when (text) {
            "C" -> {
                currentValue = "0"
                previousValue = "0"
                operationStack.clear()
                editText.text = currentValue
            }
            "%" -> {
                currentValue = calculatePercentage(currentValue)
                editText.text = currentValue
            }
            "+/-" -> {
                currentValue = changeSign(currentValue)
                editText.text = currentValue
            }
            "/" -> {
                currentOperation = "/"
                operationStack.add(currentValue)
                operationStack.add("/")
                currentValue = "0"
                editText.text = currentValue
            }
            "*" -> {
                currentOperation = "*"
                operationStack.add(currentValue)
                operationStack.add("*")
                currentValue = "0"
                editText.text = currentValue
            }
            "-" -> {
                currentOperation = "-"
                operationStack.add(currentValue)
                operationStack.add("-")
                currentValue = "0"
                editText.text = currentValue
            }
            "+" -> {
                currentOperation = "+"
                operationStack.add(currentValue)
                operationStack.add("+")
                currentValue = "0"
                editText.text = currentValue
            }
            "=" -> {
                operationStack.add(currentValue)
                val result = calculateResult(operationStack)
                editText.text = result.toString()
                currentValue = result.toString()
                previousValue = result.toString()
                operationStack.clear()
            }
            "." -> {
                if (!currentValue.contains(".")) {
                    currentValue += "."
                    editText.text = currentValue
                }
            }
            else -> {
                if (currentValue == "0") {
                    currentValue = text
                } else {
                    currentValue += text
                }
                editText.text = currentValue
            }
        }
    }

    private fun calculatePercentage(value: String): String {
        val percentage = value.toDouble() / 100
        return percentage.toString()
    }

    private fun changeSign(value: String): String {
        val signChanged = -value.toDouble()
        return signChanged.toString()
    }

    private fun calculateResult(operationStack: MutableList<String>): Double {
        var result = operationStack[0].toDouble()
        for (i in 1 until operationStack.size step 2) {
            val operation = operationStack[i]
            val value = operationStack[i + 1].toDouble()
            when (operation) {
                "+" -> result += value
                "-" -> result -= value
                "*" -> result *= value
                "/" -> result /= value
            }
        }
        return result
    }
}