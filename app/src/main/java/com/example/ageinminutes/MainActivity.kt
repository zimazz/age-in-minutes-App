package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_click_me = findViewById<Button>(R.id.btnDatePicker)
        btn_click_me.setOnClickListener {view ->
            clickDatePicker(view)

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selecteDay ->
                //Toast.makeText(this@MainActivity, "DatePicker works", Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteDay/${selectedMonth + 1}/$selectedYear"
                val text_id: TextView = findViewById(R.id.tvSelectedDate)

                text_id.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateToMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                val date_id: TextView = findViewById(R.id.tvSelectedDateInMinutes)
                date_id.setText(differenceInMinutes.toString())

            }
            , year
            , month
            , day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}