package org.android.bbangzip.presentation.component.wheelpicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.Semester
import org.android.bbangzip.presentation.type.SemesterType
import org.android.bbangzip.presentation.util.constant.DateConstant

@Composable
fun BbangZipSemesterPicker(
    onConfirm: (Semester) -> Unit = {},
    currentSemester: Semester = Semester(DateConstant.YEAR_OF_TODAY.toString(), "1학기"),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val years = DateConstant.YEARS_LIST
        val yearPickerState = remember { mutableStateOf("") }
        val startYear = currentSemester.year.toInt() - DateConstant.YEAR_OF_TODAY

        val semesters = SemesterType.entries.map { entry -> entry.text }
        val semesterPickerState = remember { mutableStateOf("") }
        val startSemester = SemesterType.entries.find { it.text == currentSemester.semester }?.ordinal

        LaunchedEffect(yearPickerState.value, semesterPickerState.value) {
            val year = yearPickerState.value.filter { it.isDigit() }
            val semester = semesterPickerState.value
            onConfirm(
                Semester(
                    year = year,
                    semester = semester,
                ),
            )
        }

        Picker(
            state = yearPickerState,
            items = years,
            modifier = Modifier.weight(5f),
            startIndex = startYear,
            isCircular = false,
        )

        Picker(
            state = semesterPickerState,
            items = semesters,
            modifier = Modifier.weight(4f),
            startIndex = startSemester ?: 0,
            isCircular = false,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BbangZipSemesterPickerPreview() {
    var selectedSemester by remember { mutableStateOf<Semester?>(null) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        BbangZipSemesterPicker(
            onConfirm = {
                selectedSemester = it
            },
            currentSemester = Semester("2025", "2학기"),
        )
        Text("Selected Semester: ${selectedSemester?.year}년 ${selectedSemester?.semester}")
    }
}
