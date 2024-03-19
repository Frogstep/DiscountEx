package io.ilyasin.discountex.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateTimeUtils {
    fun formatDate(milliseconds: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val date = Date(milliseconds)
        return sdf.format(date)
    }

    private const val MILLISECONDS_IN_A_MINUTE = 60000
    fun getMillisecondsTillNextMinute(): Long {
        return MILLISECONDS_IN_A_MINUTE - System.currentTimeMillis() % MILLISECONDS_IN_A_MINUTE
    }
}