package com.example.airtickets.ui.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.DatePicker
import com.example.airtickets.ui.utils.converters.DateTimeConverter
import java.util.Calendar
import java.util.Date

class CustomDatePicker(
    activity: Activity,
    private val setDate: ((date: Date) -> Unit)
): DateTimeConverter {
    private val calendar: Calendar = Calendar.getInstance()

    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    private val datePickerDialog = DatePickerDialog(
        activity,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, dayOfMonth: Int ->
            val selectedDateFormatted = Calendar.getInstance().apply {
                set(Calendar.YEAR, selectedYear)
                set(Calendar.MONTH, selectedMonth)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }.time
            setDate(selectedDateFormatted)
        },
        year,
        month,
        day
    )

    fun show() {
        datePickerDialog.show()
    }
}
