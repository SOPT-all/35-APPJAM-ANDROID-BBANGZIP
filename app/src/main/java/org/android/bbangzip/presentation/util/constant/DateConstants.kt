package org.android.bbangzip.presentation.util.constant

import java.time.LocalDate

object DateConstants {
    val YEAR_OF_TODAY = LocalDate.now().year
    val YEARS_LIST = (YEAR_OF_TODAY..2028).map { it.toString() + "년" }
    val MONTHS_LIST = (1..12).map { it.toString() + "월" }
}
