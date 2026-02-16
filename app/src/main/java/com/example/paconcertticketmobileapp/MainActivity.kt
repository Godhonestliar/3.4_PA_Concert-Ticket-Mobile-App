package com.example.paconcertticketmobileapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtTickets = findViewById<EditText>(R.id.txtTickets)
        val spnEvents = findViewById<Spinner>(R.id.spnEvents)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCalculate.setOnClickListener {
            val numberOfTicketsStr = txtTickets.text.toString()
            
            if (numberOfTicketsStr.isNotEmpty()) {
                val numberOfTickets = numberOfTicketsStr.toInt()
                val selectedEventIndex = spnEvents.selectedItemPosition
                
                // Pop Music Festival - $49.99
                // Jazz Music Festival - $45.99
                // Country Music Festival - $42.99
                val ticketPrices = doubleArrayOf(49.99, 45.99, 42.99)
                
                val totalCost = numberOfTickets * ticketPrices[selectedEventIndex]
                
                val df = DecimalFormat("$###,###.00")
                txtResult.text = getString(R.string.total_cost_label) + df.format(totalCost)
            } else {
                txtResult.text = "Please enter number of tickets"
            }
        }
    }
}
