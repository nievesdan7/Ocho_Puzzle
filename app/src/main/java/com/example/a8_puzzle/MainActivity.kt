package com.example.a8_puzzle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    //Controls
    private lateinit var BTNButtons : Array<Button>
    private lateinit var TXVMessage: TextView
    private lateinit var BTNRestart: Button
    private lateinit var BTNDisorder: Button
    private lateinit var BTNVerify: Button
    private lateinit var Tablero: Array<Array<String>>

    //Variables
    private val rows=4
    private val cols=4


    //Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Variables
        BTNButtons = arrayOf(
            findViewById(R.id.BTN00), findViewById(R.id.BTN01), findViewById(R.id.BTN02), findViewById(R.id.BTN03),
            findViewById(R.id.BTN10), findViewById(R.id.BTN11), findViewById(R.id.BTN12), findViewById(R.id.BTN13),
            findViewById(R.id.BTN20), findViewById(R.id.BTN21), findViewById(R.id.BTN22), findViewById(R.id.BTN23),
            findViewById(R.id.BTN30), findViewById(R.id.BTN31), findViewById(R.id.BTN32), findViewById(R.id.BTN33)
        )
        TXVMessage = findViewById(R.id.TXVMessage)
        BTNRestart = findViewById(R.id.BTNRestart)
        BTNDisorder = findViewById(R.id.BTNDisorder)
        BTNVerify = findViewById(R.id.BTNVerify)

        Tablero = Array(rows) { Array(cols) { "" } }

        paintButtons()

        initialTable();

        for(number in BTNButtons.indices){
            BTNButtons[number].setOnClickListener {
                change(number)
            }
        }
    }

    private fun paintButtons() {
        for(number in BTNButtons.indices){
            BTNButtons[number].setBackgroundColor(getColor(R.color.green_700))
        }
    }


    private fun initialTable() {
        var cont = 1
        for(i in 0 until rows)
        {
            for(j in 0 until cols)
            {
                if(i==rows-1&&j==cols-1)
                {
                    Tablero[i][j]="";
                }
                else
                {
                    Tablero[i][j]=cont.toString()
                    cont++
                }
            }
        }
        actualizeButtons()
    }

    private fun change(number: Int) {
        val i = number/cols
        val j = number%rows
        val neighbor = listOf(
            Pair(i-1,j),
            Pair(i+1,j),
            Pair(i,j-1),
            Pair(i,j+1)
        )
        for ((ni,nj) in neighbor){
            if(ni in 0 until rows && nj in 0 until cols){
                if(Tablero[ni][nj].isEmpty()){
                    Tablero[ni][nj]=Tablero[i][j]
                    Tablero[i][j]=""
                    actualizeButtons()
                    break
                }
            }
        }
    }

    private fun actualizeButtons() {
        for (i in 0 until rows){
            for (j in 0 until cols){
                val num=i*rows+j
                BTNButtons[num].text=Tablero[i][j]
                if(Tablero[i][j].isEmpty()){
                    BTNButtons[num].visibility= View.INVISIBLE
                }else{
                    BTNButtons[num].visibility= View.VISIBLE
                }
            }
        }
    }

}