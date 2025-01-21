package org.android.bbangzip.presentation.util.date

import org.android.bbangzip.presentation.model.Date
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun divideDatesByN(endDate: LocalDate, n: Int): List<LocalDate> {
    val today = LocalDate.now()
    val daysBetween = ChronoUnit.DAYS.between(today, endDate).toInt()

    val interval = daysBetween / n
    return (0 until n).map { today.plusDays((it * interval).toLong()) } + endDate
}

fun localDateToDate(localDate: LocalDate): Date{
    return Date(localDate.year.toString(), localDate.monthValue.toString(), localDate.dayOfMonth.toString())
}

fun dateStringToLocalDate(dateString: String): LocalDate {
    val (year, month, day) = dateString.split(" ").map { it.dropLast(1).toInt() }
    return LocalDate.of(year, month, day)
}

fun main(){
    println(divideDatesByN(LocalDate.of(2025,10,10), n = 3))
}