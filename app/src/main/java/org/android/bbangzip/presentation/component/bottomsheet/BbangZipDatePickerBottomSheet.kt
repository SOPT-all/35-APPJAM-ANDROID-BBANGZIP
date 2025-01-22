package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.wheelpicker.BbangZipDatePicker
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipDatePickerBottomSheet(
    isBottomSheetVisible: Boolean,
    modifier: Modifier = Modifier,
    bottomSheetTitle: String,
    selectedDate: Date,
    onSelectedDateChanged: (Date) -> Unit = {},
    onClickInputButton: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = bottomSheetTitle,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 15.dp),
                style = BbangZipTheme.typography.headline1Bold,
                color = BbangZipTheme.colors.labelNeutral_282119_88,
            )
        },
        content = {
            Spacer(modifier = Modifier.height(24.dp))

            BbangZipDatePicker(
                onConfirm = onSelectedDateChanged,
                currentDate = selectedDate,
            )

            Spacer(modifier = Modifier.height(24.dp))
        },
        interactButton = {
            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = onClickInputButton,
                label = "시험 일자 입력하기",
                isEnable = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BbangZipDatePickerBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }

    BbangZipDatePickerBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        bottomSheetTitle = "언제까지 공부할까요?",
        onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
        selectedDate = Date("2025", "1", "21"),
    )
}
