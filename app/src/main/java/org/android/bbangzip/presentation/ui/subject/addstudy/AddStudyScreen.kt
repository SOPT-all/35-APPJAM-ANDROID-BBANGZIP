package org.android.bbangzip.presentation.ui.subject.addstudy

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipDatePickerBottomSheet
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipListPickerBottomSheet
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.textfield.BbangZipBasicTextField
import org.android.bbangzip.presentation.component.textfield.BbangZipSimpleTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.type.AddStudyViewType
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun AddStudyScreen(
    state: AddStudyContract.AddStudyState,
    onStudyContentChange: (String) -> Unit = {},
    onStartPageChange: (String) -> Unit = {},
    onEndPageChange: (String) -> Unit = {},
    onSelectedDateChange: (Date) -> Unit = {},
    onStudyContentFocusChange: (Boolean) -> Unit = {},
    onStartPageFocusChange: (Boolean) -> Unit = {},
    onEndPageFocusChange: (Boolean) -> Unit = {},
    onShowDatePickerBtnClick: () -> Unit = {},
    onPieceNumberClick: (Int) -> Unit = {},
    onBackIconClick: () -> Unit = {},
    onSplitBtnClick: () -> Unit = {},
    onCancleIconClick: () -> Unit = {},
    onConfirmDateBtnClick: () -> Unit = {},
    onReSplitBtnClick: (Int) -> Unit = {},
    onAddStudyBtnClick: () -> Unit = {},
    onDirectEnrollBtnClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    (LocalView.current.context as Activity).window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF),
    ) {
        BbangZipBaseTopBar(
            onLeadingIconClick = onBackIconClick,
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            title = state.subjectName,
        )

        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            ShowDatePickerBtn(
                isDatePickerEnabled = state.isDatePickerEnabled,
                examDate = state.examDate,
                onShowDatePickerBtnClick = onShowDatePickerBtnClick
            )

            Gap(height = 50)

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.add_study_study_content_field_label),
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Gap(height = 16)

                BbangZipBasicTextField(
                    leadingIcon = R.drawable.ic_book_default_24,
                    placeholder = R.string.add_study_study_content_placeholder,
                    guideline = R.string.add_study_study_content_guideline,
                    value = state.studyContent ?: "",
                    onValueChange = { onStudyContentChange(it) },
                    onFocusChange = { onStudyContentFocusChange(it) },
                    maxCharacter = 20,
                    onDeleteButtonClick = { onCancleIconClick() },
                    focusManager = focusManager,
                    bbangZipTextFieldInputState = state.studyContentTextFieldInputState,
                )
            }

            Gap(height = 32)

            when (state.addStudyViewType) {
                AddStudyViewType.DEFAULT -> {
                    DefaultRangeView(
                        startPage = state.startPage ?: "",
                        onStartPageChange = onStartPageChange,
                        onStartPageFocusChange = onStartPageFocusChange,
                        focusManager = focusManager,
                        endPage = state.endPage ?: "",
                        onEndPageChange = onEndPageChange,
                        onEndPageFocusChange = onEndPageFocusChange,
                        onSplitBtnClick = onSplitBtnClick,
                        isSplitBtnEnabled = state.isSplitBtnEnabled,
                        startPageTextFieldInputState = state.startPageTextFieldInputState,
                        endPageTextFieldInputState = state.endPageTextFieldInputState,
                        startPageGuideline = state.startPageGuideline,
                        endPageGuideline = state.endPageGuideline,
                    )
                }

                AddStudyViewType.AGAIN -> {
                    AgainRangeView(
                        focusManager = focusManager,
                        pieceNumber = state.pieceNumber,
                        onSplitBtnClick = onReSplitBtnClick,
                        isSplitBtnEnabled = state.isSplitBtnEnabled,
                    )
                }
            }

            Gap(height = 8)

            Text(
                text = stringResource(R.string.add_study_split_btn_description),
                style = BbangZipTheme.typography.caption2Bold,
                color = BbangZipTheme.colors.labelAssistive_282119_28,
            )

            Gap()

            BbangZipButton(
                bbangZipButtonSize = BbangZipButtonSize.Large,
                bbangZipButtonType = BbangZipButtonType.Solid,
                onClick = {
                    if (state.addStudyViewType == AddStudyViewType.DEFAULT) onDirectEnrollBtnClick()
                    else onAddStudyBtnClick() },
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.btn_enroll_study_label),
                trailingIcon = R.drawable.ic_plus_thick_24,
                isEnable = state.isEnrollBtnEnabled,
            )
        }

        BbangZipDatePickerBottomSheet(
            isBottomSheetVisible = state.isDatePickerBottomSheetVisible,
            bottomSheetTitle = stringResource(R.string.add_study_date_picker_bottomsheet_title),
            selectedDate = state.selectedDate,
            onSelectedDateChanged = onSelectedDateChange,
            onClickInputButton = onConfirmDateBtnClick,
            onDismissRequest = onShowDatePickerBtnClick,
        )

        BbangZipListPickerBottomSheet(
            isBottomSheetVisible = state.isPiecePickerBottomSheetVisible,
            itemList = List(6){ stringResource(R.string.add_study_piece_selection_bottom_sheet_item, it + 1) },
            title = {
                Text(
                    text = stringResource(R.string.add_study_piece_selection_bottom_sheet_description),
                )
            },
            onSelectedItemChanged = onPieceNumberClick,
            onDismissRequest = onSplitBtnClick,
        )
    }
}

@Composable
private fun ShowDatePickerBtn(
    isDatePickerEnabled: Boolean,
    examDate: String,
    onShowDatePickerBtnClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.add_study_show_date_picker_field_label),
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Gap(height = 16)

        Box(
            modifier =
            Modifier
                .applyFilterOnClick(
                    radius = 20.dp,
                    isDisabled = isDatePickerEnabled,
                ) {
                    if (isDatePickerEnabled) onShowDatePickerBtnClick()
                }
                .fillMaxWidth()
                .background(
                    color = BbangZipTheme.colors.fillNormal_68645E_08,
                    shape = RoundedCornerShape(20.dp),
                )
                .padding(start = 16.dp)
                .padding(vertical = 18.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar_default_24),
                    contentDescription = null,
                    tint = if (examDate == "시험 일자 입력") BbangZipTheme.colors.labelAssistive_282119_28 else BbangZipTheme.colors.labelNormal_282119,
                    modifier =
                    Modifier
                        .padding(2.dp)
                        .size(16.dp),
                )

                Gap(width = 8)

                Text(
                    text = examDate,
                    style = BbangZipTheme.typography.label1Medium,
                    color = if (examDate == "시험 일자 입력") BbangZipTheme.colors.labelAssistive_282119_28 else BbangZipTheme.colors.labelNormal_282119,
                )
            }
        }
    }
}

@Composable
private fun DefaultRangeView(
    startPage: String,
    onStartPageChange: (String) -> Unit,
    onStartPageFocusChange: (Boolean) -> Unit,
    focusManager: FocusManager,
    startPageTextFieldInputState: BbangZipTextFieldInputState,
    startPageGuideline: String,
    endPageTextFieldInputState: BbangZipTextFieldInputState,
    endPageGuideline: String,
    endPage: String,
    onEndPageChange: (String) -> Unit,
    onEndPageFocusChange: (Boolean) -> Unit,
    onSplitBtnClick: () -> Unit,
    isSplitBtnEnabled: Boolean,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.add_study_study_range_label),
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Gap(height = 16)

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_start_page_placeholder,
                guideline = startPageGuideline,
                value = startPage,
                bbangZipTextFieldInputState = startPageTextFieldInputState,
                modifier = Modifier.weight(1f),
                onValueChange = {
                    onStartPageChange(it)
                },
                onFocusChange = { onStartPageFocusChange(it) },
                focusManager = focusManager,
            )

            Gap(width = 16)

            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_end_page_placeholder,
                guideline = endPageGuideline,
                value = endPage,
                bbangZipTextFieldInputState = endPageTextFieldInputState,
                modifier = Modifier.weight(1f),
                onValueChange = {
                    onEndPageChange(it)
                },
                onFocusChange = { onEndPageFocusChange(it) },
                focusManager = focusManager,
            )
        }

        Gap(height = 16)

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Outlined,
            bbangZipButtonSize = BbangZipButtonSize.Medium,
            onClick = {
                onSplitBtnClick()
            },
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.btn_slice_study_label),
            isEnable = isSplitBtnEnabled,
        )
    }
}

@Composable
private fun AgainRangeView(
    focusManager: FocusManager,
    pieceNumber: Int,
    onSplitBtnClick: (Int) -> Unit,
    isSplitBtnEnabled: Boolean,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.add_study_study_range_label),
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Gap(height = 16)

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_start_page_placeholder,
                guideline = stringResource(R.string.add_study_start_page_guideline),
                value = stringResource(R.string.add_study_start_piece_label),
                modifier = Modifier.weight(1f),
                onValueChange = {},
                onFocusChange = { },
                focusManager = focusManager,
            )

            Gap(width = 16)

            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_end_page_placeholder,
                guideline = stringResource(R.string.add_study_end_page_guideline),
                value = stringResource(R.string.add_study_piece_number, pieceNumber),
                modifier = Modifier.weight(1f),
                onValueChange = { },
                onFocusChange = { },
                focusManager = focusManager,
            )
        }

        Gap(height = 16)

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Outlined,
            bbangZipButtonSize = BbangZipButtonSize.Medium,
            onClick = {
                onSplitBtnClick(pieceNumber)
            },
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.add_study_re_split_btn_label),
            isEnable = isSplitBtnEnabled,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddStudyScreenPreview() {
    AddStudyScreen(
        state = AddStudyContract.AddStudyState()
    )
}
