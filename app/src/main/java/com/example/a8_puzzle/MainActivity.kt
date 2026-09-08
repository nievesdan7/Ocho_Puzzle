package com.example.a8_puzzle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity: AppCompatActivity() {
    //Controls
    private lateinit var BTNTable : Array<Button>
    private lateinit var TXVMessage: TextView
    private lateinit var BTNRestart: Button
    private lateinit var BTNDisorder: Button
    private lateinit var BTNVerify: Button
    private lateinit var Table: Array<Array<String>>

    //Variables
    private val rows=4
    private val cols=4


    //Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Variables
        BTNTable = arrayOf(
            findViewById(R.id.BTN00),
            findViewById(R.id.BTN01),
            findViewById(R.id.BTN02),
            findViewById(R.id.BTN03),
            findViewById(R.id.BTN10),
            findViewById(R.id.BTN11),
            findViewById(R.id.BTN12),
            findViewById(R.id.BTN13),
            findViewById(R.id.BTN20),
            findViewById(R.id.BTN21),
            findViewById(R.id.BTN22),
            findViewById(R.id.BTN23),
            findViewById(R.id.BTN30),
            findViewById(R.id.BTN31),
            findViewById(R.id.BTN32),
            findViewById(R.id.BTN33)
        )
        TXVMessage = findViewById(R.id.TXVMessage)
        BTNRestart = findViewById(R.id.BTNRestart)
        BTNDisorder = findViewById(R.id.BTNDisorder)
        BTNVerify = findViewById(R.id.BTNVerify)

        Table = Array(rows) { Array(cols) { "" } }
    }

}