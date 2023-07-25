package com.miftahulhudaf.sahabatbencana.data.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateHelper {
    fun getCurrentDateTimeWithTimeZoneOffset(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val timeZone = TimeZone.getTimeZone("GMT+0700")

        dateFormat.timeZone = timeZone

        return dateFormat.format(currentDate)
    }

    fun getDateTimeOneWeekAgoWithTimeZoneOffset(): String {
        val currentDate = Date()
        val calendar = Calendar.getInstance().apply {
            time = currentDate
            add(Calendar.DAY_OF_MONTH, -7)
        }
        val dateOneWeekAgo = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val timeZone = TimeZone.getTimeZone("GMT+0700")

        dateFormat.timeZone = timeZone

        return dateFormat.format(dateOneWeekAgo)
    }

    fun selectedDateLabel(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM yyyy (HH:mm)", Locale.getDefault())

        val start = getDateTimeOneWeekAgoWithTimeZoneOffset()
        val end = getCurrentDateTimeWithTimeZoneOffset()

        val dateStart = inputFormat.parse(start)
        val dateEnd = inputFormat.parse(end)

        return "${outputFormat.format(dateStart)} - ${outputFormat.format(dateEnd)}"
    }
}