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

        BTNRestart.setOnClickListener {
            restartTablero()
        }

        BTNDisorder.setOnClickListener {
            disorderTablero()
        }

        BTNVerify.setOnClickListener {
            verifyTablero()
        }
    }
    private fun restartTablero() {
        initialTable()
        TXVMessage.text=getString(R.string.messageRestart)
    }
    private fun disorderTablero(){
        val elements = mutableListOf<String>()

        for (i in 1..15) {
            elements.add(i.toString())
        }
        elements.add("")

        elements.shuffle()

        var num = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                Tablero[i][j] = elements[num]
                num++
            }
        }

        actualizeButtons()
        TXVMessage.text = getString(R.string.messagedisorder)
    }
    private fun verifyTablero() {
        /*var flag = true
        var cont = 1

        for (i in 0 until rows) {
            for (j in 0 until cols) {

                if (i == rows - 1 && j == cols - 1) {
                    if (Tablero[i][j].isNotEmpty()) {
                        flag = false
                    }
                } else {

                    if (Tablero[i][j] != cont.toString()) {
                        flag = false
                    }
                    cont++
                }
            }*/
        val recorridoEspiral = mutableListOf<String>()

        var filaInicio = 0
        var filaFin = rows - 1
        var colInicio = 0
        var colFin = cols - 1

        // 1. Leer el tablero siguiendo la ruta de la espiral
        while (filaInicio <= filaFin && colInicio <= colFin) {
            // De izquierda a derecha
            for (j in colInicio..colFin) {
                recorridoEspiral.add(Tablero[filaInicio][j])
            }
            filaInicio++

            // De arriba hacia abajo
            for (i in filaInicio..filaFin) {
                recorridoEspiral.add(Tablero[i][colFin])
            }
            colFin--

            // De derecha a izquierda
            if (filaInicio <= filaFin) {
                for (j in colFin downTo colInicio) {
                    recorridoEspiral.add(Tablero[filaFin][j])
                }
                filaFin--
            }

            // De abajo hacia arriba
            if (colInicio <= colFin) {
                for (i in filaFin downTo filaInicio) {
                    recorridoEspiral.add(Tablero[i][colInicio])
                }
                colInicio++
            }
        }

        // 2. Crear la lista con la solución esperada: ["1", "2", ..., "15", ""]
        val solucionGanadora = (1..15).map { it.toString() }.toMutableList().apply { add("") }

        // 3. Comparar si el estado actual es igual a la solución ganadora
        val flag = recorridoEspiral == solucionGanadora



        if (flag) {
            TXVMessage.text = getString(R.string.messageverifyWin)
        } else {
            TXVMessage.text = getString(R.string.messageverifyNoWin)
        }
    }
    private fun initialTable() {
        var cont = 1
        /*for(i in 0 until rows)
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
        }*/
        val numeros = (1..15).toMutableList()
        var filaInicio = 0
        var filaFin = 3
        var colInicio = 0
        var colFin = 3

        var index = 0

        // 4. Lógica de llenado en espiral
        while (filaInicio <= filaFin && colInicio <= colFin) {

            for (j in colInicio..colFin) {
                // Si ya usamos los 15 números, ponemos el 0 en la última posición restante
                Tablero[filaInicio][j] = if (index < 15) numeros[index++].toString() else ""
            }
            filaInicio++

            // De arriba hacia abajo
            for (i in filaInicio..filaFin) {
                Tablero[i][colFin] = if (index < 15) numeros[index++].toString() else ""
            }
            colFin--

            // De derecha a izquierda
            if (filaInicio <= filaFin) {
                for (j in colFin downTo colInicio) {
                    Tablero[filaFin][j] = if (index < 15) numeros[index++].toString() else ""
                }
                filaFin--
            }

            // De abajo hacia arriba
            if (colInicio <= colFin) {
                for (i in filaFin downTo filaInicio) {
                    Tablero[i][colInicio] = if (index < 15) numeros[index++].toString() else ""
                }
                colInicio++
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
                //Aquí hago invisible al boton vacío
                if(Tablero[i][j].isEmpty()){
                    BTNButtons[num].visibility= View.INVISIBLE
                }else{
                    BTNButtons[num].visibility= View.VISIBLE
                }
            }
        }
    }

    //Aquí repinto los botones
    private fun paintButtons() {
        for(number in BTNButtons.indices){
            BTNButtons[number].setBackgroundColor(getColor(R.color.green_700))
        }
    }
}