package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val luckyNumber: Int = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollBtn.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        // Create new Dice object with 12 sides and roll it
        val dice = Dice(12)
        val rollResult = dice.roll()
        val rollResult2 = dice.roll()

        tvNumber.text = rollResult.toString()
        tvNumber2.text = rollResult2.toString()

        checkPlayerWin(rollResult, rollResult2)
    }

    private fun checkPlayerWin(player1Roll: Int, player2Roll: Int) {

        when (player1Roll) {
            luckyNumber -> {
                Toast.makeText(this, "Both players won!", Toast.LENGTH_SHORT).show()
                disableButton()
            }
        }

        if (player1Roll == luckyNumber && player2Roll == luckyNumber) {
            Toast.makeText(this, "Both players won!", Toast.LENGTH_SHORT).show()
            disableButton()
        } else if (player1Roll == luckyNumber) {
            Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show()
            disableButton()
        } else if (player2Roll == luckyNumber) {
            Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show()
            disableButton()
        }
    }

    private fun disableButton() {
        rollBtn.isEnabled = false
    }
}

class Dice(private val numSides: Int) {
    fun roll() = (1..numSides).random()
}