package org.android.bbangzip.presentation.component.wheelpicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import java.time.YearMonth

@Composable
fun BbangZipDatePicker(){
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        val years = (2025..2035).map { it.toString() + "년" }
        val yearPickerState = rememberPickerState()

        val months = (1..12).map { it.toString() + "월" }
        val monthsPickerState = rememberPickerState()

        val days = remember(yearPickerState.selectedItem, monthsPickerState.selectedItem) {
            derivedStateOf {
                val year = yearPickerState.selectedItem.filter { it.isDigit() }.toIntOrNull() ?: 2025
                val month = monthsPickerState.selectedItem.filter { it.isDigit() }.toIntOrNull() ?: 1
                (1..getDaysInMonth(year, month)).map { it.toString() + "일" }
            }
        }
        val daysPickerState = rememberPickerState()

        Picker(
            state = yearPickerState,
            items = years,
            visibleItemsCount = 5,
            modifier = Modifier.weight(0.4f),
            textModifier = Modifier.padding(8.dp),
            isCircular = true
        )

        Picker(
            state = monthsPickerState,
            items = months,
            visibleItemsCount = 5,
            modifier = Modifier.weight(0.3f),
            textModifier = Modifier.padding(8.dp),
            isCircular = true
        )

        Picker(
            state = daysPickerState,
            items = days.value,
            startIndex = 0, //if(daysPickerState.selectedItem == "") daysPickerState.selectedItem.toInt() else 0,
            visibleItemsCount = 5,
            modifier = Modifier.weight(0.3f),
            textModifier = Modifier.padding(8.dp),
            isCircular = true
        )
    }
}

fun getDaysInMonth(year: Int, month: Int): Int {
    return YearMonth.of(year, month).lengthOfMonth()
}

@Preview(showBackground = true)
@Composable
fun BbangZipDatePickerPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BbangZipDatePicker()
    }
}