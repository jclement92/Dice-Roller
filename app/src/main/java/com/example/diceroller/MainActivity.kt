package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var rolled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollBtn.setOnClickListener {
            rollDice()
        }
        rollDice()
    }

    private fun rollDice() {
        // Create new Dice object with 12 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        val dice2 = Dice(6)
        val diceRoll2 = dice2.roll()

        val drawableResource = setDiceImage(diceRoll)
        val drawableResource2 = setDiceImage(diceRoll2)

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()

        diceImage2.setImageResource(drawableResource2)
        diceImage2.contentDescription = diceRoll2.toString()

        if (rolled) checkPlayerWin(diceRoll, diceRoll2)

        rolled = true
    }

    private fun setDiceImage(playerRoll: Int): Int {
        return when (playerRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun checkPlayerWin(playerRoll: Int, playerRoll2: Int, luckyNumber: Int = 4) {
        if (playerRoll == luckyNumber && playerRoll2 == luckyNumber) {
            disableButton()
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show()
        } else if (playerRoll == luckyNumber) {
            disableButton()
            Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show()
        } else if (playerRoll2 == luckyNumber) {
            disableButton()
            Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun disableButton() {
        rollBtn.isEnabled = false
    }
}

class Dice(private val numSides: Int) {
    fun roll() = (1..numSides).random()
}