package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ageinminutesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttondate.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

      val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view,year, month, dayofmonth ->

                val selectedDated    =  "$dayofmonth/${month+1}/${year}"
                binding.selectedDate.setText(selectedDated)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDated)

                val selectedDateInMinutes = theDate!!.time/60000
                val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentdate!!.time/60000
                val differentInMinutes = currentDateToMinutes - selectedDateInMinutes

                binding.textview5.setText(differentInMinutes.toString())
        }

            ,year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time - 8640000)
        dpd.show()
    }
}