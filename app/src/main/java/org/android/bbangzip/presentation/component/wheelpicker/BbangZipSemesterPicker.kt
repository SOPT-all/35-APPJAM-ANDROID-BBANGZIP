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
import org.android.bbangzip.presentation.util.constant.DateConstants
import timber.log.Timber

@Composable
fun BbangZipSemesterPicker(
    onConfirm: (Semester) -> Unit = {},
    currentSemester: Semester = Semester(DateConstants.YEAR_OF_TODAY.toString(), SemesterType.FIRST),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val years = DateConstants.YEARS_LIST
        var yearPickerState by remember { mutableStateOf(currentSemester.year) }
        val startYear = currentSemester.year.toInt() - DateConstants.YEAR_OF_TODAY

        val semesters = SemesterType.entries.map { entry -> entry.text }
        var semesterPickerState by remember { mutableStateOf(currentSemester.semester.text) }
        val startSemester = SemesterType.entries.find { it == currentSemester.semester }?.ordinal

        LaunchedEffect(yearPickerState, semesterPickerState) {
            val year = yearPickerState.filter { it.isDigit() }
            val semester = SemesterType.entries.find { it.text == semesterPickerState }
            Timber.d("[온보딩] semester picker -> $year , $semester")
            onConfirm(
                Semester(
                    year = year,
                    semester = semester ?: SemesterType.FIRST,
                ),
            )
        }

        BbangZipPicker(
            items = years,
            modifier = Modifier.weight(5f),
            startIndex = startYear,
            onItemChanged = { selectedYear ->
                yearPickerState = selectedYear
            },
        )

        BbangZipPicker(
            items = semesters,
            modifier = Modifier.weight(4f),
            startIndex = startSemester ?: 0,
            onItemChanged = { selectedSemester ->
                semesterPickerState = selectedSemester
            },
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
            currentSemester = Semester("2025", SemesterType.SECOND),
        )
        Text("Selected Semester: ${selectedSemester?.year}년 ${selectedSemester?.semester}")
    }
}
