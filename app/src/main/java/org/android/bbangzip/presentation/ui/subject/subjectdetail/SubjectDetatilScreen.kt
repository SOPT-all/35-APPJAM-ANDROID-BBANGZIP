package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipBasicModalBottomSheet
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipGetBadgeBottomSheet
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.ToDoCard
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.AddStudyViewType
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun SubjectDetailScreen(
    state: SubjectDetailContract.SubjectDetailState,
    onRevertCompleteBottomSheetDismissBtnClick: () -> Unit = {},
    onRevertCompleteBottomSheetApproveBtnClick: (Int) -> Unit = {},
    onRevertCompleteBottomSheetDismissRequest: () -> Unit = {},
    onTrashIconClick: () -> Unit = {},
    onCloseIconClick: () -> Unit = {},
    onDeleteModePieceCardClick: (Int) -> Unit = {},
    onDeleteBtnClick: () -> Unit = {},
    onEnrollMotivationMessageClick: (Int, String) -> Unit = { _, _ -> },
    onModifySubjectNameClick: (Int, String) -> Unit = { _, _ -> },
    onKebabIconClick: () -> Unit = {},
    onTabClick: (Int) -> Unit = {},
    onAddStudyCardClick: (SplitStudyData) -> Unit = {},
    onAddStudyBtnClick: (SplitStudyData) -> Unit = {},
    onPlusIconClick: (SplitStudyData) -> Unit = {},
    onDefaultModePieceCardClick: (Int) -> Unit = {},
    onCompleteModePieceCardClick: (Int) -> Unit = {},
    onGetBadgeBottomSheetCloseBtnClick: () -> Unit = {},
    onMenuDismissRequest: () -> Unit = {},
    navigateToBack: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val backgroundHeight = (screenHeightDp * 0.32).toInt()

    val scrollState = rememberLazyListState()
    val isShadowed by remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 0
        }
    }

    val tabs = listOf("중간고사", "기말고사")
    val splitStudyData =
        SplitStudyData(
            subjectId = state.subjectId,
            subjectName = state.subjectName,
            pieceNumber = 0,
            examDate = state.examDate.ifEmpty { stringResource(R.string.subject_detail_default_exam_date) },
            examName = state.examName,
            studyContent = "",
            startPage = "",
            endPage = "",
            startPageList = emptyList(),
            endPageList = emptyList(),
            deadLineList = emptyList(),
            addStudyViewType = AddStudyViewType.DEFAULT,
        )

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        onMenuDismissRequest()
                    }
                },
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
            state = scrollState,
        ) {
            item {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(backgroundHeight.dp)
                            .background(
                                color = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                            ),
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_subject_detail_header),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .padding(top = 60.dp)
                                .fillMaxSize()
                                .aspectRatio(360f / 172f)
                                .clip(shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                                .align(Alignment.BottomCenter),
                    )

                    TwoLineTextWithWordWrap(text = state.motivationMessage.ifEmpty { stringResource(R.string.subject_detail_default_motivation_message) })

                    ExamTabRow(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        tabs = tabs,
                        state = state,
                        onTabClick = onTabClick,
                    )
                }
            }

            item {
                when (state.pieceViewType) {
                    PieceViewType.EMPTY -> {
                        Spacer(modifier = Modifier.height(84.dp))
                        EmptySubjectCardView(
                            splitStudyData = splitStudyData,
                            onAddStudyBtnClick = onAddStudyBtnClick,
                        )
                    }

                    PieceViewType.DEFAULT -> {
                        DefaultPieceView(
                            todoList = state.todoList,
                            onTrashIconClick = onTrashIconClick,
                            onDefaultModePieceCardClick = onDefaultModePieceCardClick,
                            onCompleteModePieceCardClick = onCompleteModePieceCardClick,
                            dDay = state.examDDay.toString(),
                            examDay = state.examDate,
                            splitStudyData = splitStudyData,
                            onAddStudyCardClick = onAddStudyCardClick,
                            onPlusIconClick = onPlusIconClick,
                        )
                    }

                    PieceViewType.DELETE -> {
                        DeletePieceView(
                            todoList = state.todoList,
                            dDay = state.examDDay.toString(),
                            examDay = state.examDate,
                            onCloseIconClick = onCloseIconClick,
                            onDeleteModePieceCardClick = onDeleteModePieceCardClick,
                        )
                    }
                }
            }
        }

        Column {
            BbangZipBaseTopBar(
                isShadowed = isShadowed,
                backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
                trailingIcon = R.drawable.ic_menu_kebab_default_24,
                onTrailingIconClick = onKebabIconClick,
                onLeadingIconClick = navigateToBack,
                title = state.subjectName,
            )

            if (state.isMenuOpen) {
                MenuBox(
                    modifier = Modifier.align(Alignment.End),
                    onEnrollMotivationMessageClick = onEnrollMotivationMessageClick,
                    state = state,
                    onModifySubjectNameClick = onModifySubjectNameClick,
                )
            }
        }

        if (state.pieceViewType == PieceViewType.DELETE) {
            Box(
                modifier =
                    Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                BbangZipButton(
                    bbangZipButtonType = BbangZipButtonType.Solid,
                    bbangZipButtonSize = BbangZipButtonSize.Large,
                    onClick = onDeleteBtnClick,
                    modifier = Modifier.fillMaxWidth(),
                    label = if (state.selectedPiecesToDelete.isEmpty()) stringResource(R.string.btn_default_delete_label) else stringResource(R.string.btn_delete_label, state.selectedPiecesToDelete.size),
                    trailingIcon = R.drawable.ic_trash_default_24,
                    isEnable = state.selectedPiecesToDelete.isNotEmpty(),
                )
            }
        }

        RevertCompleteBottomSheet(
            modifier = Modifier.padding(bottom = 16.dp),
            isBottomSheetVisible = state.isRevertCompleteBottomSheetVisible,
            bottomSheetTitle = stringResource(R.string.revert_complete_bottom_sheet_title),
            selectedCompletePieceId = state.selectedPieceId,
            onDismissRequest = onRevertCompleteBottomSheetDismissRequest,
            onApproveBtnClick = onRevertCompleteBottomSheetApproveBtnClick,
            onCancelBtnClick = onRevertCompleteBottomSheetDismissBtnClick,
        )

        if (state.badgeList.isNotEmpty()) {
            BbangZipGetBadgeBottomSheet(
                badgeList = state.badgeList,
                isBottomSheetVisible = state.isGetBadgeBottomSheetVisible,
                onDismissRequest = onGetBadgeBottomSheetCloseBtnClick,
                onClickCancelButton = onGetBadgeBottomSheetCloseBtnClick,
            )
        }
    }
}

@Composable
private fun DefaultPieceView(
    todoList: List<ToDoCardModel>,
    dDay: String,
    examDay: String,
    splitStudyData: SplitStudyData,
    onTrashIconClick: () -> Unit = {},
    onDefaultModePieceCardClick: (Int) -> Unit,
    onCompleteModePieceCardClick: (Int) -> Unit,
    onAddStudyCardClick: (SplitStudyData) -> Unit = {},
    onPlusIconClick: (SplitStudyData) -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Gap(height = 52)

        PieceViewDateRow(
            dDay = dDay,
            examDay = examDay,
        )

        Gap(height = 40)

        DefaultPieceViewTopBar(
            onTrashIconClick = onTrashIconClick,
            onPlusIconClick = { onPlusIconClick(splitStudyData) },
        )

        Gap(height = 16)

        todoList.forEach { item ->
            ToDoCard(
                data = item,
                onClick = {
                    if (item.cardState == BbangZipCardState.COMPLETE) {
                        onCompleteModePieceCardClick(item.pieceId)
                    } else {
                        onDefaultModePieceCardClick(item.pieceId)
                    }
                },
            )

            Gap(height = 12)
        }

        AddStudyCard { onAddStudyCardClick(splitStudyData) }

        Gap(height = 20)
    }
}

@Composable
private fun DeletePieceView(
    todoList: List<ToDoCardModel>,
    dDay: String,
    examDay: String,
    onCloseIconClick: () -> Unit,
    onDeleteModePieceCardClick: (Int) -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Gap(height = 52)

        PieceViewDateRow(
            dDay = dDay,
            examDay = examDay,
        )

        Gap(height = 40)

        DeletePieceViewTopBar(onCloseIconClick = onCloseIconClick)

        Gap(height = 16)

        todoList.forEach { item ->
            ToDoCard(
                data = item,
                onClick = {
                    onDeleteModePieceCardClick(item.pieceId)
                },
            )

            Gap(height = 12)
        }

        Gap(height = 64)
    }
}

@Composable
private fun DefaultPieceViewTopBar(
    onTrashIconClick: () -> Unit = {},
    onPlusIconClick: () -> Unit = {},
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.subject_detail_default_view_description),
            style = BbangZipTheme.typography.headline2Bold,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash_default_24),
            contentDescription = null,
            modifier =
                Modifier
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                    ) { onTrashIconClick() }
                    .padding(8.dp),
            tint = BbangZipTheme.colors.labelAlternative_282119_61,
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus_default_24),
            contentDescription = null,
            modifier =
                Modifier
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                        onClick = onPlusIconClick,
                    )
                    .padding(8.dp),
            tint = BbangZipTheme.colors.labelAlternative_282119_61,
        )
    }
}

@Composable
private fun DeletePieceViewTopBar(
    onCloseIconClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.subject_detail_delete_view_description),
            style = BbangZipTheme.typography.headline2Bold,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
        )

        Gap()

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_x_small_24),
            contentDescription = null,
            modifier =
                Modifier
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                        onClick = onCloseIconClick,
                    )
                    .padding(8.dp),
            tint = BbangZipTheme.colors.labelAlternative_282119_61,
        )
    }
}

@Composable
private fun PieceViewDateRow(
    dDay: String,
    examDay: String,
) {
    Row(
        modifier = Modifier.padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.subject_detail_exam_d_day, dDay),
            style = BbangZipTheme.typography.caption1Medium,
            color = BbangZipTheme.colors.staticWhite_FFFFFF,
            modifier =
                Modifier
                    .background(
                        color = BbangZipTheme.colors.statusPositive_3D3730,
                        shape = RoundedCornerShape(11.dp),
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = examDay,
            style = BbangZipTheme.typography.caption1Medium,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
        )
    }
}

@Composable
private fun MenuBox(
    modifier: Modifier = Modifier,
    onEnrollMotivationMessageClick: (Int, String) -> Unit,
    state: SubjectDetailContract.SubjectDetailState,
    onModifySubjectNameClick: (Int, String) -> Unit,
) {
    Box(
        modifier =
            modifier
                .padding(end = 16.dp)
                .height(128.dp)
                .width(200.dp)
                .applyShadows(BbangZipShadowType.HEAVY, shape = RoundedCornerShape(32.dp))
                .offset(y = (-8).dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        color = BbangZipTheme.colors.staticWhite_FFFFFF,
                        shape = RoundedCornerShape(32.dp),
                    )
                    .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.subject_detail_navigate_to_modify_motivation_message_menu_label),
                style = BbangZipTheme.typography.body1Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .applyFilterOnClick(
                            radius = 16.dp,
                            isDisabled = false,
                        ) {
                            onEnrollMotivationMessageClick(
                                state.subjectId,
                                state.subjectName,
                            )
                        }
                        .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
            )

            Gap()

            Text(
                text = stringResource(R.string.subject_detail_navigate_to_modify_subject_name_menu_label),
                style = BbangZipTheme.typography.body1Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .applyFilterOnClick(
                            radius = 16.dp,
                            isDisabled = false,
                        ) {
                            onModifySubjectNameClick(
                                state.subjectId,
                                state.subjectName,
                            )
                        }
                        .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
            )
        }
    }
}

@Composable
private fun ExamTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    state: SubjectDetailContract.SubjectDetailState,
    onTabClick: (Int) -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = 32.dp)
                .height(64.dp)
                .background(
                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                    shape = RoundedCornerShape(32.dp),
                )
                .applyShadows(
                    BbangZipShadowType.EMPHASIZE,
                    shape = RoundedCornerShape(32.dp),
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        color = BbangZipTheme.colors.staticWhite_FFFFFF,
                        shape = RoundedCornerShape(32.dp),
                    )
                    .padding(horizontal = 76.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            tabs.forEachIndexed { index, tabName ->
                ExamTab(
                    text = tabName,
                    isSelected = state.tabIndex == index,
                    onClick = {
                        onTabClick(index)
                    },
                )
            }
        }
    }
}

@Composable
private fun ExamTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.noRippleClickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            style = BbangZipTheme.typography.body1Bold,
            color = if (isSelected) BbangZipTheme.colors.labelNormal_282119 else BbangZipTheme.colors.labelAssistive_282119_28,
        )

        Gap(height = 4)

        Box(
            modifier =
                Modifier
                    .height(2.dp)
                    .width(40.dp)
                    .background(color = if (isSelected) BbangZipTheme.colors.labelNormal_282119 else BbangZipTheme.colors.staticWhite_FFFFFF),
        )
    }
}

@Composable
private fun AddStudyCard(
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = BbangZipTheme.colors.lineAlternative_68645E_08,
                    shape = RoundedCornerShape(24.dp),
                )
                .applyFilterOnClick(
                    radius = 24.dp,
                    isDisabled = false,
                    onClick = onClick,
                )
                .padding(vertical = 23.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        color = BbangZipTheme.colors.lineNormal_68645E_22,
                        shape = CircleShape,
                    ),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus_default_24),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
            )
        }

        Gap(width = 8)

        Text(
            text = stringResource(R.string.card_add_study_description),
            color = BbangZipTheme.colors.labelDisable_282119_12,
        )
    }
}

@Composable
private fun EmptySubjectCardView(
    splitStudyData: SplitStudyData,
    modifier: Modifier = Modifier,
    onAddStudyBtnClick: (SplitStudyData) -> Unit = {},
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_view),
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 15f),
        )

        Gap(16)

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onAddStudyBtnClick(splitStudyData) },
            label = stringResource(R.string.btn_add_todo_label),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = R.drawable.ic_plus_thick_24,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevertCompleteBottomSheet(
    isBottomSheetVisible: Boolean,
    bottomSheetTitle: String,
    selectedCompletePieceId: Int,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onApproveBtnClick: (Int) -> Unit = {},
    onCancelBtnClick: () -> Unit = {},
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
        interactButton = {
            Gap(height = 16)

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onApproveBtnClick(selectedCompletePieceId) },
                label = stringResource(R.string.todo_revert_bottomsheet_approve_text),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        cancelButton = {
            Gap(height = 8)

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Outlined,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = onCancelBtnClick,
                label = stringResource(R.string.btn_cancle_label),
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

@Composable
fun TwoLineTextWithWordWrap(
    text: String,
) {
    var displayText by remember(text) { mutableStateOf<AnnotatedString?>(null) }

    BasicText(
        text = displayText ?: AnnotatedString(text),
        modifier =
            Modifier
                .width(230.dp)
                .padding(top = 92.dp, start = 20.dp),
        style = BbangZipTheme.typography.heading2Bold,
        onTextLayout = { textLayoutResult ->
            if (displayText == null) {
                displayText = processTextForWordWrap(text, textLayoutResult, 2)
            }
        },
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = { defaultBbangZipColors.labelAlternative_282119_61 },
    )
}

private fun processTextForWordWrap(
    text: String,
    textLayoutResult: TextLayoutResult,
    maxLines: Int,
): AnnotatedString {
    val lines = textLayoutResult.lineCount.coerceAtMost(maxLines)
    if (lines <= 1) return AnnotatedString(text)

    val builder = AnnotatedString.Builder()
    var startIndex = 0

    for (lineIndex in 0 until lines) {
        val endIndex = textLayoutResult.getLineEnd(lineIndex, visibleEnd = false).coerceAtMost(text.length)
        val lineText = text.substring(startIndex, endIndex)

        if (lineIndex == lines - 1 && endIndex < text.length) {
            val lastSpaceIndex = lineText.lastIndexOf(' ')
            if (lastSpaceIndex != -1) {
                builder.append(lineText.substring(0, lastSpaceIndex))
                builder.append("\n")
                builder.append(text.substring(startIndex + lastSpaceIndex + 1))
                break
            }
        }

        builder.append(lineText)
        if (lineIndex < lines - 1) builder.append("\n")
        startIndex = endIndex
    }

    return builder.toAnnotatedString()
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun SubjectDetailScreenPreview() {
    SubjectDetailScreen(
        state = SubjectDetailContract.SubjectDetailState(),
    )
}
