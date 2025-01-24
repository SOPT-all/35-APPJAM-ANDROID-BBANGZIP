package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import timber.log.Timber

@Composable
fun SubjectDetailScreen(
    padding: PaddingValues,
    state: SubjectDetailContract.SubjectDetailState,
    isMenuOpen: Boolean,
    todoList: List<ToDoCardModel>,
    pieceViewType: PieceViewType,
    deletedSet: Set<Int>,
    revertCompleteBottomSheetState: Boolean,
    selectedItemId: Int,
    subjectId: Int,
    subjectName: String,
    motivationMessage: String,
    examDDay: Int,
    examDate: String,
    examName: String,
    onRevertCompleteBottomSheetDismissButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetApproveButtonClicked: (Int) -> Unit = {},
    onRevertCompleteBottomSheetDismissRequest: () -> Unit = {},
    onTrashIconClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onDeleteModeCardClicked: (Int) -> Unit = {},
    onClickCancleBtn: () -> Unit = {},
    onClickEnrollMotivationMessage: (Int, String) -> Unit = { _, _ -> },
    onClickModifySubjectName: (Int, String) -> Unit = { _, _ -> },
    onClickKebabMenu: () -> Unit = {},
    onClickTab: (Int) -> Unit = {},
    onClickAddStudy: (SplitStudyData) -> Unit = {},
    onDefaultCardClicked: (Int) -> Unit = {},
    onCompleteCardClicked: (Int) -> Unit = {},
    onClickBadgeCloseBtn: () -> Unit,
    popBackStack: () -> Unit,
) {
    Timber.tag("김재민").d("SubjectDetailScreen : $subjectName $examName")
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
    var selectedIndex by remember { mutableIntStateOf(0) }
    val splitStudyData =
        SplitStudyData(
            subjectId = subjectId,
            subjectName = subjectName,
            pieceNumber = 0,
            examDate = "시험 일자 입력",
            examName = examName,
            studyContent = "",
            startPage = "",
            endPage = "",
            startPageList = emptyList(),
            endPageList = emptyList(),
            deadLineList = emptyList(),
            addStudyViewType = AddStudyViewType.DEFAULT,
        )

    Timber.d("${deletedSet.size}")
    Box(
        modifier = Modifier.fillMaxSize(),
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
                    TwoLineTextWithWordWrap(motivationMessage)

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .offset(y = 32.dp)
                                .height(64.dp)
                                .background(
                                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                                    shape = RoundedCornerShape(32.dp),
                                )
                                .align(Alignment.BottomCenter)
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
                                examTab(
                                    text = tabName,
                                    isSelected = selectedIndex == index,
                                    onClick = {
                                        onClickTab(index)
                                        selectedIndex = index
                                    },
                                )
                            }
                        }
                    }
                }
            }
            item {
                when (pieceViewType) {
                    PieceViewType.EMPTY -> {
                        Spacer(modifier = Modifier.height(84.dp))
                        EmptySubjectCardView(
                            splitStudyData = splitStudyData,
                            onClickAddStudy = onClickAddStudy,
                        )
                    }

                    PieceViewType.DEFAULT -> {
                        DefaultPieceView(
                            todoList = todoList,
                            onTrashIconClicked = onTrashIconClicked,
                            onDefaultCardClicked = onDefaultCardClicked,
                            onCompleteCardClicked = onCompleteCardClicked,
                            dDay = examDDay.toString(),
                            examDay = examDate,
                            splitStudyData = splitStudyData,
                            onClickAddStudy = onClickAddStudy,
                        )
                    }

                    PieceViewType.DELETE -> {
                        DeletePieceView(
                            todoList = todoList,
                            onCloseIconClicked = onCloseIconClicked,
                            onDeleteModeCardClicked = onDeleteModeCardClicked,
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
                onTrailingIconClick = { onClickKebabMenu() },
                onLeadingIconClick = popBackStack,
                title = subjectName,
            )
            if (isMenuOpen) {
                Box(
                    modifier =
                        Modifier
                            .padding(end = 16.dp)
                            .height(128.dp)
                            .width(200.dp)
                            .applyShadows(BbangZipShadowType.HEAVY, shape = RoundedCornerShape(32.dp))
                            .align(Alignment.End)
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
                            text = "각오 한 마디 작성하기",
                            style = BbangZipTheme.typography.body1Bold,
                            color = BbangZipTheme.colors.labelNormal_282119,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .applyFilterOnClick(
                                        radius = 16.dp,
                                        isDisabled = false,
                                    ) {
                                        onClickEnrollMotivationMessage(subjectId, subjectName)
                                    }
                                    .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "과목명 수정하기",
                            style = BbangZipTheme.typography.body1Bold,
                            color = BbangZipTheme.colors.labelNormal_282119,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .applyFilterOnClick(
                                        radius = 16.dp,
                                        isDisabled = false,
                                    ) { onClickModifySubjectName(subjectId, subjectName) }
                                    .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
                        )
                    }
                }
            }
        }
        if (pieceViewType == PieceViewType.DELETE) {
            Box(
                modifier =
                    Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                BbangZipButton(
                    bbangZipButtonType = BbangZipButtonType.Solid,
                    bbangZipButtonSize = BbangZipButtonSize.Large,
                    onClick = {
                        onClickCancleBtn()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = if (deletedSet.isEmpty()) "삭제하기" else String.format(stringResource(R.string.btn_delete_label), deletedSet.size),
                    trailingIcon = R.drawable.ic_trash_default_24,
                    isEnable = deletedSet.isNotEmpty(),
                )
            }
        }

        RevertCompleteBottomSheet(
            isBottomSheetVisible = revertCompleteBottomSheetState,
            bottomSheetTitle = "미완료 상태로 되돌릴까요?",
            selectedCompleteItemId = selectedItemId,
            onDismissRequest = onRevertCompleteBottomSheetDismissRequest,
            onClickInteractButton = onRevertCompleteBottomSheetApproveButtonClicked,
            onClickCancelButton = onRevertCompleteBottomSheetDismissButtonClicked,
        )

        if (state.badgeList.isNotEmpty()) {
            BbangZipGetBadgeBottomSheet(
                badgeList = state.badgeList,
                isBottomSheetVisible = state.getBadgeBottomSheetState,
                onDismissRequest = { onClickBadgeCloseBtn() },
                onClickCancelButton = { onClickBadgeCloseBtn() },
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
    onTrashIconClicked: () -> Unit = {},
    onDefaultCardClicked: (Int) -> Unit,
    onCompleteCardClicked: (Int) -> Unit,
    onClickAddStudy: (SplitStudyData) -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "시험까지 D$dDay",
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

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "학습 내용",
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
                        ) { onTrashIconClicked() }
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
                        ) {
                            onClickAddStudy(splitStudyData)
                        }
                        .padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        todoList.forEach { item ->
            ToDoCard(
                data = item,
                onClick = {
                    onDefaultCardClicked(item.pieceId)
                    if (item.cardState == BbangZipCardState.COMPLETE) onCompleteCardClicked(item.pieceId)
                },
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        Box(
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
                    ) { onClickAddStudy(splitStudyData) },
        ) {
            Row(
                modifier =
                    Modifier
                        .padding(vertical = 23.dp)
                        .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
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

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "공부 추가",
                    color = BbangZipTheme.colors.labelDisable_282119_12,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun DeletePieceView(
    todoList: List<ToDoCardModel>,
    onCloseIconClicked: () -> Unit,
    onDeleteModeCardClicked: (Int) -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(52.dp))

        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "시험까지 D-14",
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
                text = "2025년 11월 25일",
                style = BbangZipTheme.typography.caption1Medium,
                color = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "삭제할 항목을 눌러서 선택해 주세요",
                style = BbangZipTheme.typography.headline2Bold,
                color = BbangZipTheme.colors.labelAlternative_282119_61,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_x_small_24),
                contentDescription = null,
                modifier =
                    Modifier
                        .applyFilterOnClick(
                            radius = 20.dp,
                            isDisabled = false,
                        ) { onCloseIconClicked() }
                        .padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        todoList.forEach { item ->
            ToDoCard(
                data = item,
                onClick = {
                    onDeleteModeCardClicked(item.pieceId)
                },
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun examTab(
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

        Spacer(Modifier.height(4.dp))

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

@Composable
private fun EmptySubjectCardView(
    modifier: Modifier = Modifier,
    splitStudyData: SplitStudyData,
    onAddSubjectButtonClicked: () -> Unit = {},
    onClickAddStudy: (SplitStudyData) -> Unit = {},
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(328.dp)
                    .background(color = BbangZipTheme.colors.backgroundAlternative_F5F5F5, shape = RoundedCornerShape(size = 32.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.empty_view_text),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onClickAddStudy(splitStudyData) },
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
    selectedCompleteItemId: Int,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onClickInteractButton: (Int) -> Unit = {},
    onClickCancelButton: () -> Unit = {},
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
            Spacer(modifier = Modifier.height(16.dp))

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onClickInteractButton(selectedCompleteItemId) },
                label = stringResource(R.string.todo_revert_bottomsheet_approve_text),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        cancelButton = {
            Spacer(modifier = Modifier.height(8.dp))

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Outlined,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = onClickCancelButton,
                label = stringResource(R.string.btn_cancle_label),
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun SubjectDetailScreenPreview() {
    SubjectDetailScreen(
        padding = PaddingValues(64.dp),
        state = SubjectDetailContract.SubjectDetailState(),
        todoList = emptyList(),
        pieceViewType = PieceViewType.DEFAULT,
        deletedSet = emptySet(),
        isMenuOpen = false,
        selectedItemId = 0,
        subjectId = 0,
        subjectName = "",
        revertCompleteBottomSheetState = true,
        motivationMessage = "사장님의 각오 한마디를 작성해보세요",
        examDate = "2025년 1월 1일",
        examDDay = 14,
        examName = "중간고사",
        onClickBadgeCloseBtn = { },
        popBackStack = { },
    )
}
