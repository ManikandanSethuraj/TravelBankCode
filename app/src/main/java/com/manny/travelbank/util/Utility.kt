package com.manny.travelbank.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("###0.00")
        println("RoundOFFValue::${df.format(number)}")
        return df.format(number).toDouble()
    }

     fun Date.dateToString(): String {
        val dateFormatter = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault())
        return dateFormatter.format(this)
    }

    fun Date.dateToStringAdapter(): String {
        val dateFormatter = SimpleDateFormat("MMM dd", Locale.getDefault())
        return dateFormatter.format(this)
    }
     fun formatDate(dateToFormat : String) : String{
         println("DateCamHere::$dateToFormat")
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(dateToFormat)
         println("SendingDate:$date")
        return date.dateToString()
    }

     fun formatDateAdapter(dateToFormat : String) : String{
        val format = SimpleDateFormat("MM-dd")
        val date: Date = format.parse(dateToFormat)
        return date.dateToStringAdapter()
    }

}