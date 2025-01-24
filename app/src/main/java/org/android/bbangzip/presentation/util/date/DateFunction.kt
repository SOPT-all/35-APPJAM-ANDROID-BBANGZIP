package org.android.bbangzip.presentation.util.date

import org.android.bbangzip.presentation.model.Date
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun divideDatesByN(
    endDate: LocalDate,
    n: Int,
): List<LocalDate> {
    val today = LocalDate.now()
    val daysBetween = ChronoUnit.DAYS.between(today, endDate).toInt()

    val interval = daysBetween / n
    return (0 until n).map { today.plusDays((it * interval).toLong()) } + endDate
}

fun localDateToDate(localDate: LocalDate): Date {
    return Date(localDate.year.toString(), localDate.monthValue.toString(), localDate.dayOfMonth.toString())
}

fun dateStringToLocalDate(dateString: String): LocalDate {
    val (year, month, day) = dateString.split(" ").map { it.dropLast(1).toInt() }
    return LocalDate.of(year, month, day)
}

fun hyponStringDateToDate(dateString: String): Date {
    val (year, month, day) = dateString.split("-").map { it.toInt() }
    return Date(year.toString(), month.toString(), day.toString())
}

fun formatHyponDate(input: String): String {
    val parts = input.split("-")
    val year = parts[0]
    val month = parts[1].toInt() // 월을 정수로 변환해 앞의 0 제거
    val day = parts[2].toInt() // 일을 정수로 변환해 앞의 0 제거
    return "${year}년 ${month}월 ${day}일"
}

fun formatDate(input: String): String {
    val parts = input.split("년", "월", "일").map { it.trim() }
    return "${parts[0]}-${parts[1].padStart(2, '0')}-${parts[2].padStart(2, '0')}"
}

fun addLeadingZero(date: String): String {
    val parts = date.split("-")
    if (parts.size == 3) {
        val year = parts[0]
        val month = parts[1].padStart(2, '0')
        val day = parts[2].padStart(2, '0')
        return "$year-$month-$day"
    } else {
        throw IllegalArgumentException("잘못된 날짜 형식입니다: $date")
    }
}

fun dateToString(date: Date): String {
    return "${date.year}-${date.month}-${date.day}"
}

fun main() {
    println(divideDatesByN(LocalDate.of(2025, 10, 10), n = 3))
}
