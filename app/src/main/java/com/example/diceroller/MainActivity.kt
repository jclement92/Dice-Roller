package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        setDiceImage(diceRoll)
        //checkPlayerWin(diceRoll)
    }

    private fun setDiceImage(playerRoll: Int) {
        val drawableResource = when (playerRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = playerRoll.toString()
    }

    private fun checkPlayerWin(playerRoll: Int, luckyNumber: Int = 4) {
        when (playerRoll) {
            luckyNumber -> Toast.makeText(this, "Player won!", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, "So sorry! You rolled a 1. Try again!", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(this, "Sadly, you rolled a 2. Try again!", Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(this, "Unfortunately, you rolled a 3. Try again!", Toast.LENGTH_SHORT).show()
            4 -> Toast.makeText(this, "No luck! You rolled a 4. Try again!", Toast.LENGTH_SHORT).show()
            5 -> Toast.makeText(this, "Don't cry! You rolled a 5. Try again!", Toast.LENGTH_SHORT).show()
            6 -> Toast.makeText(this, "Apologies! you rolled a 6. Try again!", Toast.LENGTH_SHORT).show()
        }

        if (playerRoll == luckyNumber) disableButton()
    }

    private fun disableButton() {
        rollBtn.isEnabled = false
    }
}

class Dice(private val numSides: Int) {
    fun roll() = (1..numSides).random()
}