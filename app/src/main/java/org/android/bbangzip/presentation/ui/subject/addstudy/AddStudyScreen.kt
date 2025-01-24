package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
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
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

@Composable
fun AddStudyScreen(
    padding: PaddingValues,
    pieceNumber: Int,
    subjectTitle: String = "",
    examDate: String = "",
    examName: String = "",
    studyContent: String = "",
    startPage: String = "",
    startGuideline: String = "",
    endPage: String = "",
    endGuideline: String = "",
    selectedDate: Date,
    datePickerBottomSheetState: Boolean = false,
    piecePickerBottomSheetState: Boolean = false,
    isButtonEnable: Boolean = false,
    isSplitBtnEnable: Boolean = false,
    isDatePickerEnable: Boolean,
    studyContentTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    startPageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    endPageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    addStudyViewType: AddStudyViewType = AddStudyViewType.DEFAULT,
    onChangeStudyContent: (String) -> Unit = {},
    onChangeStartPage: (String) -> Unit = {},
    onChangeEndPage: (String) -> Unit = {},
    onChangeSelectedDate: (Date) -> Unit = {},
    onChangeStudyContentFocused: (Boolean) -> Unit = {},
    onChangeStartPageFocused: (Boolean) -> Unit = {},
    onChangeEndPageFocused: (Boolean) -> Unit = {},
    onClickDatePicker: () -> Unit = {},
    onClickPieceNumber: (Int) -> Unit = {},
    onClickBackIcon: () -> Unit = {},
    onClickSplitBtn: () -> Unit = {},
    onClickCancleIcon: () -> Unit = {},
    onClickConfirmDateBtn: () -> Unit = {},
    onClickAgainSplitBtn: (Int) -> Unit = {},
    onClickAddStudyBtn: () -> Unit = {},
    onClickDirectEnrollBtn: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Timber.tag("김재민").d("AddStudyScreenExamName : $examName")

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .addFocusCleaner(focusManager)
                .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF),
    ) {
        BbangZipBaseTopBar(
            onLeadingIconClick = { onClickBackIcon() },
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            title = subjectTitle,
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "시험 일자",
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier =
                        Modifier
                            .applyFilterOnClick(
                                radius = 20.dp,
                                isDisabled = isDatePickerEnable
                                ) {
                                if(isDatePickerEnable) onClickDatePicker()
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

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = examDate,
                            style = BbangZipTheme.typography.label1Medium,
                            color = if (examDate == "시험 일자 입력") BbangZipTheme.colors.labelAssistive_282119_28 else BbangZipTheme.colors.labelNormal_282119,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "학습 내용",
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.height(16.dp))

                BbangZipBasicTextField(
                    leadingIcon = R.drawable.ic_book_default_24,
                    placeholder = R.string.add_study_study_content_placeholder,
                    guideline = R.string.add_study_study_content_guideline,
                    value = studyContent,
                    onValueChange = { onChangeStudyContent(it) },
                    onFocusChange = { onChangeStudyContentFocused(it) },
                    maxCharacter = 20,
                    onDeleteButtonClick = { onClickCancleIcon() },
                    focusManager = focusManager,
                    bbangZipTextFieldInputState = studyContentTextFieldState,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            when (addStudyViewType) {
                AddStudyViewType.DEFAULT -> {
                    DefaultRangeView(
                        startPage = startPage,
                        onChangeStartPage = onChangeStartPage,
                        onChangeStartPageFocused = onChangeStartPageFocused,
                        focusManager = focusManager,
                        endPage = endPage,
                        onChangeEndPage = onChangeEndPage,
                        onChangeEndPageFocused = onChangeEndPageFocused,
                        onClickSplitBtn = onClickSplitBtn,
                        isSplitBtnEnable = isButtonEnable,
                        startPageTextFieldState = startPageTextFieldState,
                        endPageTextFieldState = endPageTextFieldState,
                        startGuideline = startGuideline,
                        endGuideline = endGuideline,
                        addStudyViewType = addStudyViewType,
                        onClickDirectEnrollBtn = onClickDirectEnrollBtn
                    )
                }

                AddStudyViewType.AGAIN -> {
                    AgainRangeView(
                        focusManager = focusManager,
                        pieceNumber = pieceNumber,
                        onClickSplitBtn = onClickAgainSplitBtn,
                        isSplitBtnEnable = isButtonEnable,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "[Tip] 입력하신 학습 범위를 자동으로 분배하여\n꾸준히 공부할 수 있도록 학습 계획을 세워드려요!",
                style = BbangZipTheme.typography.caption2Bold,
                color = BbangZipTheme.colors.labelAssistive_282119_28,
            )

            Spacer(modifier = Modifier.weight(1f))

            BbangZipButton(
                bbangZipButtonSize = BbangZipButtonSize.Large,
                bbangZipButtonType = BbangZipButtonType.Solid,
                onClick = { if(addStudyViewType == AddStudyViewType.DEFAULT) onClickDirectEnrollBtn() else onClickAddStudyBtn() },
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.btn_enroll_study_label),
                trailingIcon = R.drawable.ic_plus_thick_24,
                isEnable = isButtonEnable,
            )
        }

        BbangZipDatePickerBottomSheet(
            isBottomSheetVisible = datePickerBottomSheetState,
            bottomSheetTitle = stringResource(R.string.add_study_date_picker_bottomsheet_title),
            selectedDate = selectedDate,
            onSelectedDateChanged = onChangeSelectedDate,
            onClickInputButton = onClickConfirmDateBtn,
            onDismissRequest = onClickDatePicker,
        )

        BbangZipListPickerBottomSheet(
            isBottomSheetVisible = piecePickerBottomSheetState,
            itemList = listOf("1조각", "2조각", "3조각", "4조각", "5조각", "6조각"),
            title = {
                Text(
                    text = "몇 조각으로 쪼개서 공부할까요?",
                )
            },
            onSelectedItemChanged = onClickPieceNumber,
            onDismissRequest = onClickSplitBtn,
        )
    }
}

@Composable
private fun DefaultRangeView(
    startPage: String,
    onChangeStartPage: (String) -> Unit,
    onChangeStartPageFocused: (Boolean) -> Unit,
    focusManager: FocusManager,
    startPageTextFieldState: BbangZipTextFieldInputState,
    startGuideline: String,
    endPageTextFieldState: BbangZipTextFieldInputState,
    endGuideline: String,
    endPage: String,
    addStudyViewType: AddStudyViewType,
    onChangeEndPage: (String) -> Unit,
    onChangeEndPageFocused: (Boolean) -> Unit,
    onClickSplitBtn: () -> Unit,
    onClickDirectEnrollBtn: () -> Unit,
    isSplitBtnEnable: Boolean,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "학습 범위 ",
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_start_page_placeholder,
                guideline = startGuideline,
                value = startPage,
                bbangZipTextFieldInputState = startPageTextFieldState,
                modifier = Modifier.weight(1f),
                onValueChange = {
                    onChangeStartPage(it)
                },
                onFocusChange = { onChangeStartPageFocused(it) },
                focusManager = focusManager,
            )

            Spacer(modifier = Modifier.width(16.dp))
            Timber.tag("error").d("endPage : $endPageTextFieldState")
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_end_page_placeholder,
                guideline = endGuideline,
                value = endPage,
                bbangZipTextFieldInputState = endPageTextFieldState,
                modifier = Modifier.weight(1f),
                onValueChange = {
                    onChangeEndPage(it)
                },
                onFocusChange = { onChangeEndPageFocused(it) },
                focusManager = focusManager,
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    BbangZipButton(
        bbangZipButtonType = BbangZipButtonType.Outlined,
        bbangZipButtonSize = BbangZipButtonSize.Medium,
        onClick = {
            onClickSplitBtn()
        },
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(R.string.btn_slice_study_label),
        isEnable = isSplitBtnEnable,
    )
}

@Composable
private fun AgainRangeView(
    focusManager: FocusManager,
    pieceNumber: Int,
    onClickSplitBtn: (Int) -> Unit,
    isSplitBtnEnable: Boolean,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "학습 범위 ",
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_start_page_placeholder,
                guideline = stringResource(R.string.add_study_start_page_guideline),
                value = "1조각",
                modifier = Modifier.weight(1f),
                onValueChange = {
                },
                onFocusChange = { },
                focusManager = focusManager,
            )

            Spacer(modifier = Modifier.width(16.dp))

            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_end_page_placeholder,
                guideline = stringResource(R.string.add_study_end_page_guideline),
                value = "${pieceNumber}조각",
                modifier = Modifier.weight(1f),
                onValueChange = {
                },
                onFocusChange = { },
                focusManager = focusManager,
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    BbangZipButton(
        bbangZipButtonType = BbangZipButtonType.Outlined,
        bbangZipButtonSize = BbangZipButtonSize.Medium,
        onClick = {
            onClickSplitBtn(pieceNumber)
        },
        modifier = Modifier.fillMaxWidth(),
        label = "다시 쪼개기",
        isEnable = isSplitBtnEnable,
    )
}

@Preview(showSystemUi = true)
@Composable
fun AddStudyScreenPreview() {
    AddStudyScreen(
        padding = PaddingValues(),
        selectedDate = Date("2025", "1", "21"),
        pieceNumber = 3,
        isDatePickerEnable = true
    )
}
