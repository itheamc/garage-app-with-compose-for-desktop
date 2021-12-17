package utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.days

fun Long.toDate(): String {
    return SimpleDateFormat.getDateInstance().format(Date(this))
}