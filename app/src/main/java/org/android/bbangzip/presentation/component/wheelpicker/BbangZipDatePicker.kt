package org.android.bbangzip.presentation.component.wheelpicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.util.constant.DateConstant
import java.time.YearMonth

@Composable
fun BbangZipDatePicker(
    onConfirm: (Date) -> Unit = {},
    currentDate: Date = Date(DateConstant.YEAR_OF_TODAY.toString(), "1", "1"),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val years = DateConstant.YEARS_LIST
        val yearPickerState = remember { mutableStateOf("") }
        val startYear = currentDate.year.toInt() - DateConstant.YEAR_OF_TODAY

        val months = DateConstant.MONTHS_LIST
        val monthsPickerState = remember { mutableStateOf("") }
        val startMonth = currentDate.month.toInt() - 1

        val days =
            remember(yearPickerState.value, monthsPickerState.value) {
                derivedStateOf {
                    val year = yearPickerState.value.filter { it.isDigit() }.toIntOrNull() ?: DateConstant.YEAR_OF_TODAY
                    val month = monthsPickerState.value.filter { it.isDigit() }.toIntOrNull() ?: 1
                    (1..getDaysInMonth(year, month)).map { it.toString() + "일" }
                }
            }
        val daysPickerState = remember { mutableStateOf("") }
        val startDay = currentDate.day.toInt() - 1

        LaunchedEffect(yearPickerState.value, monthsPickerState.value, daysPickerState.value) {
            val year = yearPickerState.value.filter { it.isDigit() }
            val month = monthsPickerState.value.filter { it.isDigit() }
            val day = daysPickerState.value.filter { it.isDigit() }
            onConfirm(
                Date(
                    year = year,
                    month = month,
                    day = day
                )
            )
        }

        // 3개 picker에 weight 부여해서 대충 피그마와 비슷하게 동적으로 되게 구현해봤는데 어떤가요..
        Picker(
            state = yearPickerState,
            items = years,
            modifier = Modifier.weight(5f),
            startIndex = startYear,
            isCircular = false,
        )

        Picker(
            state = monthsPickerState,
            items = months,
            modifier = Modifier.weight(3f),
            startIndex = startMonth,
            isCircular = false,
        )

        Picker(
            state = daysPickerState,
            items = days.value,
            modifier = Modifier.weight(3f),
            startIndex = startDay,
            isCircular = false,
        )
    }
}

// 현재 달이 몇일 있는 지 알아낼 함수, 어디로 빼야할까요?
fun getDaysInMonth(
    year: Int,
    month: Int,
): Int {
    return YearMonth.of(year, month).lengthOfMonth()
}

@Preview(showBackground = true)
@Composable
fun BbangZipDatePickerPreview() {
    var selectedDate by remember { mutableStateOf<Date?>(null) }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BbangZipDatePicker(
            onConfirm = {
                selectedDate = it
            },
            currentDate = Date("2026", "3", "16"),
        )
        Text("Selected Date: ${selectedDate?.year}년 ${selectedDate?.month}월 ${selectedDate?.day}일")
    }
}
