package com.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var topStart: ImageView
    private lateinit var topCenter: ImageView
    private lateinit var topEnd: ImageView

    private lateinit var middleStart: ImageView
    private lateinit var middleCenter: ImageView
    private lateinit var middleEnd: ImageView

    private lateinit var bottomStart: ImageView
    private lateinit var bottomCenter: ImageView
    private lateinit var bottomEnd: ImageView

    var isPlayer1 = true;
    var gameEnd = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reset = findViewById<Button>(R.id.reset_button)

        topStart = findViewById(R.id.top_start)
        topCenter = findViewById(R.id.top_center)
        topEnd = findViewById(R.id.top_end)

        middleStart = findViewById(R.id.middle_start)
        middleCenter = findViewById(R.id.middle_center)
        middleEnd = findViewById(R.id.middle_end)

        bottomStart = findViewById(R.id.bottom_start)
        bottomCenter = findViewById(R.id.bottom_center)
        bottomEnd = findViewById(R.id.bottom_end)

        configureBox(topStart)
        configureBox(topCenter)
        configureBox(topEnd)

        configureBox(middleStart)
        configureBox(middleCenter)
        configureBox(middleEnd)

        configureBox(bottomStart)
        configureBox(bottomCenter)
        configureBox(bottomEnd)


        reset.setOnClickListener {
            resetBox(topStart)
            resetBox(topCenter)
            resetBox(topEnd)

            resetBox(middleStart)
            resetBox(middleCenter)
            resetBox(middleEnd)

            resetBox(bottomStart)
            resetBox(bottomCenter)
            resetBox(bottomEnd)

            isPlayer1 = true
            gameEnd = false
        }

    }

    private fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if (box.tag == null && !gameEnd) {
                isPlayer1 = if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_baseline_close_24)
                    box.tag = 1
                    false
                } else {
                    box.setImageResource(R.drawable.ic_outline_brightness_1_24)
                    box.tag = 2
                    true
                }

                if (playerWin(1)) {
                    Toast.makeText(this@MainActivity, "Player 1 win", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }

                if (playerWin(2)) {
                    Toast.makeText(this@MainActivity, "Player 2 win", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }

            }
        }
    }

    private fun playerWin(value: Int): Boolean {
        if ((topCenter.tag == value && middleCenter.tag == value && bottomCenter.tag == value) ||
            (topStart.tag == value && middleStart.tag == value && bottomStart.tag == value) ||
            (topEnd.tag == value && middleEnd.tag == value && bottomEnd.tag == value) ||
            (topStart.tag == value && topCenter.tag == value && topEnd.tag == value) ||
            (middleStart.tag == value && middleCenter.tag == value && middleEnd.tag == value) ||
            (bottomStart.tag == value && bottomCenter.tag == value && bottomEnd.tag == value) ||
            (topStart.tag == value && middleCenter.tag == value && bottomEnd.tag == value) ||
            (topEnd.tag == value && middleCenter.tag == value && bottomStart.tag == value)
        ) {
            return true
        }

        return false
    }

    private fun resetBox(box: ImageView) {
        box.tag = null
        box.setImageDrawable(null)
    }
}