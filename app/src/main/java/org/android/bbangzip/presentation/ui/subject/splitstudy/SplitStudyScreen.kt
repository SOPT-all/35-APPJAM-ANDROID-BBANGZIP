package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipDatePickerBottomSheet
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.component.textfield.BbangZipSimpleTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.type.AddStudyViewType
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.date.dateToString
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SplitStudyScreen(
    state: SplitStudyContract.SplitStudyState,
    onStartPageChange: (Int, String) -> Unit = { _, _ -> },
    onEndPageChange: (Int, String) -> Unit = { _, _ -> },
    onIsStartPageFocusedChange: (Int, Boolean) -> Unit = { _, _ -> },
    onIsEndPageFocusedChange: (Int, Boolean) -> Unit = { _, _ -> },
    onDeadlineChange: (Date) -> Unit = {},
    onDatePickerClick: (Int) -> Unit = {},
    onConfirmDateBtnClick: () -> Unit = {},
    onDatePickerBottomSheetDismissRequest: () -> Unit = {},
    onBackIconClick: (SplitStudyData) -> Unit = {},
    onSaveBtnClick: (SplitStudyData) -> Unit = {},
) {
    BackHandler {
        onBackIconClick(
            SplitStudyData(
                subjectName = state.subjectName,
                pieceNumber = state.pieceNumber,
                examDate = state.examDate,
                examName = state.examName,
                studyContent = state.studyContent,
                startPage = state.startPage.dropLast(1),
                endPage = state.endPage.dropLast(1),
                startPageList = state.startPageList,
                endPageList = state.endPageList,
                deadLineList = state.deadlineList.map { dateToString(it) },
                addStudyViewType = AddStudyViewType.DEFAULT,
                subjectId = state.subjectId,
            ),
        )
    }
    val focusManager = LocalFocusManager.current

    val scrollState = rememberLazyListState()
    val isShadowed by remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 0
        }
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF),
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
            state = scrollState,
        ) {
            stickyHeader {
                BbangZipBaseTopBar(
                    isShadowed = isShadowed,
                    leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
                    title = state.subjectName,
                    onLeadingIconClick = {
                        onBackIconClick(
                            SplitStudyData(
                                subjectName = state.subjectName,
                                pieceNumber = state.pieceNumber,
                                examDate = state.examDate,
                                examName = state.examName,
                                studyContent = state.studyContent,
                                startPage = state.startPage.dropLast(1),
                                endPage = state.endPage.dropLast(1),
                                startPageList = state.startPageList,
                                endPageList = state.endPageList,
                                deadLineList = state.deadlineList.map { dateToString(it) },
                                addStudyViewType = AddStudyViewType.DEFAULT,
                                subjectId = state.subjectId,
                            ),
                        )
                    },
                )
            }
            item {
                Gap(height = 24)

                StudyContentSummaryBox(
                    startPage = state.startPage,
                    endPage = state.endPage
                )

                Gap(height = 32)

                HorizontalDivider(
                    modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                )
            }

            items(
                count = state.pieceNumber,
            ) { index ->
                PieceCustomField(
                    index = index,
                    focusManager = focusManager,
                    startPageGuidelineList = state.startPageGuidelineList,
                    endPageGuidelineList = state.endPageGuidelineList,
                    startPageTextFieldInputStateList = state.startPageTextFieldInputStateList,
                    endPageTextFieldInputStateList = state.endPageTextFieldInputStateList,
                    startPageList = state.startPageList,
                    endPageList = state.endPageList,
                    deadlineList = state.deadlineList,
                    onStartPageChange = onStartPageChange,
                    onIsStartPageFocusedChange = onIsStartPageFocusedChange,
                    onEndPageChange = onEndPageChange,
                    onIsEndPageFocusedChange = onIsEndPageFocusedChange,
                    onDatePickerClick = onDatePickerClick
                )
            }

            item { Gap(height = 88) }
        }

        BbangZipButton(
            bbangZipButtonSize = BbangZipButtonSize.Large,
            bbangZipButtonType = BbangZipButtonType.Solid,
            onClick = {
                onSaveBtnClick(
                    SplitStudyData(
                        subjectName = state.subjectName,
                        pieceNumber = state.pieceNumber,
                        examDate = state.examDate,
                        examName = state.examName,
                        studyContent = state.studyContent,
                        startPage = state.startPage,
                        endPage = state.endPage,
                        startPageList = state.startPageList,
                        endPageList = state.endPageList,
                        deadLineList = state.deadlineList.map { dateToString(it) },
                        addStudyViewType = AddStudyViewType.AGAIN,
                        subjectId = state.subjectId,
                    ),
                )
            },
            modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            label = stringResource(R.string.btn_save_label),
            trailingIcon = R.drawable.ic_plus_thick_24,
            isEnable = state.isSaveEnabled,
        )
    }

    BbangZipDatePickerBottomSheet(
        isBottomSheetVisible = state.isDatePickerBottomSheetVisible,
        bottomSheetTitle = stringResource(R.string.add_study_date_picker_bottomsheet_title),
        selectedDate = state.deadlineList[state.selectedPieceIndex + 1],
        onSelectedDateChanged = onDeadlineChange,
        onClickInputButton = onConfirmDateBtnClick,
        onDismissRequest = onDatePickerBottomSheetDismissRequest,
    )
}

@Composable
private fun StudyContentSummaryBox(
    startPage: String,
    endPage: String,
) {
    Box(
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .applyShadows(
                shadowType = BbangZipShadowType.EMPHASIZE,
                shape = RoundedCornerShape(24.dp),
            )
            .background(
                color = BbangZipTheme.colors.backgroundAlternative_F5F5F5,
                shape = RoundedCornerShape(24.dp),
            )
            .padding(16.dp),
    ) {
        Column {
            Text(
                text = stringResource(R.string.split_study_study_content_summary_box_title),
                style = BbangZipTheme.typography.headline2Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
            )

            Gap(height = 8)

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BbangZipChip(
                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                    text = startPage,
                )

                Gap(width = 4)

                Text(
                    text = stringResource(R.string.split_study_start_page_suffix),
                    style = BbangZipTheme.typography.label1Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61,
                )

                Gap(width = 8)

                BbangZipChip(
                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                    text = endPage,
                )

                Gap(width = 4)

                Text(
                    text = stringResource(R.string.split_study_end_page_suffix),
                    style = BbangZipTheme.typography.label1Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61,
                )
            }
        }
    }
}

@Composable
private fun PieceCustomField(
    index: Int,
    focusManager: FocusManager,
    startPageGuidelineList: List<String>,
    endPageGuidelineList: List<String>,
    startPageTextFieldInputStateList: List<BbangZipTextFieldInputState>,
    endPageTextFieldInputStateList: List<BbangZipTextFieldInputState>,
    startPageList: List<String>,
    endPageList: List<String>,
    deadlineList: List<Date>,
    onStartPageChange: (Int, String) -> Unit,
    onIsStartPageFocusedChange: (Int, Boolean) -> Unit,
    onEndPageChange: (Int, String) -> Unit,
    onIsEndPageFocusedChange: (Int, Boolean) -> Unit,
    onDatePickerClick: (Int) -> Unit
) {
    Column(
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Gap(height = 32)

        Text(
            text = stringResource(R.string.split_study_piece_index, index + 1),
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
                guideline = startPageGuidelineList[index],
                value = startPageList[index],
                modifier = Modifier.weight(1f),
                bbangZipTextFieldInputState = startPageTextFieldInputStateList[index],
                onValueChange = {
                    onStartPageChange(index, it)
                },
                onFocusChange = { onIsStartPageFocusedChange(index, it) },
                focusManager = focusManager,
            )

            Gap(width = 16)

            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                placeholder = R.string.add_study_end_page_placeholder,
                guideline = endPageGuidelineList[index],
                value = endPageList[index],
                modifier = Modifier.weight(1f),
                bbangZipTextFieldInputState = endPageTextFieldInputStateList[index],
                onValueChange = {
                    onEndPageChange(index, it)
                },
                onFocusChange = { onIsEndPageFocusedChange(index, it) },
                focusManager = focusManager,
            )
        }

        Gap(height = 16)

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Outlined,
            bbangZipButtonSize = BbangZipButtonSize.Medium,
            onClick = {
                onDatePickerClick(index)
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = R.drawable.ic_page_check_default_24,
            label = stringResource(R.string.split_study_show_date_picker_btn_label, deadlineList[index + 1].year, deadlineList[index + 1].month, deadlineList[index + 1].day),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplitStudyScreenPreview() {
    SplitStudyScreen(
        state = SplitStudyContract.SplitStudyState()
    )
}
