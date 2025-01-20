package org.android.bbangzip.presentation.ui.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.balloon.TopTailBalloon
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipBasicModalBottomSheet
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.ToDoCard
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun TodoScreen(
    todoState: TodoContract.TodoState,
    todayDate: List<String>,
    bottomPadding : PaddingValues,
    modifier: Modifier = Modifier,
    onAddPendingStudyButtonClicked: () -> Unit = {},
    onAddStudyButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetDismissButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetApproveButtonClicked: (Int) -> Unit = {},
    onRevertCompleteBottomSheetDismissRequest: () -> Unit = {},
    onFilterIconClicked: () -> Unit = {},
    onFilterBottomSheetItemClicked: (ToDoFilterType) -> Unit = {},
    onFilterBottomSheetDismissRequest: () -> Unit = {},
    onDeleteIconClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onItemDeleteButtonClicked: () -> Unit = {},
    onDeleteScreenCardClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
    onDefaultScreenCardClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(bottomPadding)
                .padding(bottom = 10.dp)
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
    ) {
        LazyColumn {
            item {
                DateMessageCard(
                    todayDate = todayDate,
                    pendingCount = todoState.pendingCount,
                    onAddPendingStudyButtonClicked = onAddPendingStudyButtonClicked,
                )

                Spacer(Modifier.height(48.dp))
            }

            if (todoState.screenType == ToDoScreenType.EMPTY) {
                item {
                    EmptyView(onAddStudyButtonClicked = onAddStudyButtonClicked)
                }
            }

            if (todoState.screenType == ToDoScreenType.DEFAULT) {
                item {
                    StudyCountText(
                        remainingCount = todoState.remainingStudyCount,
                        completeCount = todoState.completeCount,
                    )

                    Spacer(Modifier.height(16.dp))

                    DeleteAndFilterIcons(
                        onDeleteIconClicked = onDeleteIconClicked,
                        onFilterIconClicked = onFilterIconClicked,
                    )

                    Spacer(Modifier.height(10.dp))
                }
            }

            if (todoState.screenType == ToDoScreenType.DELETE) {
                item {
                    Text(
                        text = stringResource(R.string.todo_delete_screen_text),
                        style = BbangZipTheme.typography.heading2Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                        modifier = Modifier.padding(start = 24.dp),
                    )

                    Spacer(Modifier.height(4.dp))

                    Row(
                        modifier =
                            Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier =
                                Modifier
                                    .clip(CircleShape)
                                    .clickable { onCloseIconClicked() },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_x_small_24),
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp),
                                tint = BbangZipTheme.colors.labelAlternative_282119_61,
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }

            if (todoState.screenType != ToDoScreenType.EMPTY) {
                items(
                    count = todoState.todoList.size,
                    key = { index ->
                        todoState.todoList[index].pieceId
                    },
                ) { index ->
                    ToDoCard(
                        data = todoState.todoList[index],
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        onClick = {
                            when {
                                (todoState.screenType == ToDoScreenType.DEFAULT) && (todoState.todoList[index].cardState == BbangZipCardState.DEFAULT) ->
                                    onDefaultScreenCardClicked(
                                        todoState.todoList[index].pieceId,
                                        BbangZipCardState.COMPLETE,
                                    )

                                (todoState.screenType == ToDoScreenType.DEFAULT) && (todoState.todoList[index].cardState == BbangZipCardState.COMPLETE) ->
                                    onDefaultScreenCardClicked(
                                        todoState.todoList[index].pieceId,
                                        BbangZipCardState.DEFAULT,
                                    )

                                (todoState.screenType == ToDoScreenType.DELETE) && (todoState.todoList[index].cardState == BbangZipCardState.CHECKED) ->
                                    onDeleteScreenCardClicked(
                                        todoState.todoList[index].pieceId,
                                        BbangZipCardState.CHECKABLE,
                                    )

                                (todoState.screenType == ToDoScreenType.DELETE) && (todoState.todoList[index].cardState == BbangZipCardState.CHECKABLE) ->
                                    onDeleteScreenCardClicked(
                                        todoState.todoList[index].pieceId,
                                        BbangZipCardState.CHECKED,
                                    )

                                else ->
                                    onDeleteScreenCardClicked(
                                        todoState.todoList[index].pieceId,
                                        BbangZipCardState.COMPLETE,
                                    )
                            }
                        },
                    )
                }
            }
        }
        if (todoState.screenType == ToDoScreenType.DELETE) {
            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onItemDeleteButtonClicked() },
                label = stringResource(R.string.todo_delete_screen_delete_button_text, todoState.selectedItemList.size),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 6.dp),
                isEnable = todoState.selectedItemList.isNotEmpty(),
                trailingIcon = R.drawable.ic_plus_thick_24,
            )
        }

        RevertCompleteBottomSheet(
            isBottomSheetVisible = todoState.revertCompleteBottomSheetState,
            selectedItemPieceId = todoState.selectedItemList,
            bottomSheetTitle = "미완료 상태로 되돌릴까요?",
            onDismissRequest = onRevertCompleteBottomSheetDismissRequest,
            onClickInteractButton = onRevertCompleteBottomSheetApproveButtonClicked,
            onClickCancelButton = onRevertCompleteBottomSheetDismissButtonClicked,
        )

        BbangZipToDoFilterPickerBottomSheet(
            isBottomSheetVisible = todoState.todoFilterBottomSheetState,
            selectedItem = todoState.selectedFilterItem,
            onSelectedItemChanged = onFilterBottomSheetItemClicked,
            onDismissRequest = onFilterBottomSheetDismissRequest,
        )
    }
}

@Composable
fun DateMessageCard(
    todayDate: List<String>,
    pendingCount: Int,
    onAddPendingStudyButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0, shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * (172f / 764f))
                    .padding(start = 24.dp),
            ) {
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp),
                ) {
                    Text(
                        text = todayDate[0].toInt().toString(),
                        style = BbangZipTheme.typography.title3Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )

                    Text(
                        text = stringResource(R.string.todo_month_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = todayDate[1].toInt().toString(),
                        style = BbangZipTheme.typography.title3Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )

                    Text(
                        text = stringResource(R.string.todo_day_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = todayDate[2],
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )
                }

                Spacer(modifier = modifier.height(8.dp))

                if (pendingCount == 0) {
                    Text(
                        text = stringResource(R.string.todo_pending_count_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )

                    Spacer(modifier = Modifier.height(62.dp))
                } else {
                    BbangZipButton(
                        bbangZipButtonType = BbangZipButtonType.Outlined,
                        bbangZipButtonSize = BbangZipButtonSize.Medium,
                        onClick = { onAddPendingStudyButtonClicked() },
                        label = String.format(stringResource(R.string.btn_todo_overdue_label), pendingCount),
                        trailingIcon = R.drawable.ic_chevronright_thick_small_24,
                        modifier = Modifier.wrapContentSize(),
                    )

                    Spacer(modifier = Modifier.height(48.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        TopTailBalloon(
            text = stringResource(R.string.todo_top_tail_balloon_text),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_announcement_default_24),
                    contentDescription = null,
                    tint = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.width(6.dp))
            },
            horizontalPadding = 16.dp,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun StudyCountText(
    remainingCount: Int,
    completeCount: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .padding(start = 8.dp),
    ) {
        Text(
            text =
                when {
                    completeCount > 0 && remainingCount != 0 -> stringResource(R.string.todo_complete_count_text, completeCount)
                    remainingCount == 0 -> stringResource(R.string.todo_complete_remaining_nothing_text)
                    else -> stringResource(R.string.todo_complete_nothing_text)
                },
            style = BbangZipTheme.typography.label1Bold,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
        )

        Text(
            text =
                if (remainingCount != 0) {
                    stringResource(R.string.todo_remaing_count_text, remainingCount)
                } else {
                    stringResource(R.string.todo_remaining_nothing_text)
                },
            style = BbangZipTheme.typography.title3Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )
    }
}

@Composable
fun DeleteAndFilterIcons(
    modifier: Modifier = Modifier,
    onDeleteIconClicked: () -> Unit = {},
    onFilterIconClicked: () -> Unit = {},
) {
    Row(
        modifier =
            modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier =
                Modifier
                    .clip(CircleShape)
                    .clickable { onDeleteIconClicked() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_trash_default_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }

        Box(
            modifier =
                Modifier
                    .clip(CircleShape)
                    .clickable { onFilterIconClicked() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter_default_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }
    }
}

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    onAddStudyButtonClicked: () -> Unit = {},
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
            Text(text = "Empty View")
        }

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onAddStudyButtonClicked() },
            label = stringResource(R.string.btn_add_todo_label),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = R.drawable.ic_plus_thick_24,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipToDoFilterPickerBottomSheet(
    isBottomSheetVisible: Boolean,
    modifier: Modifier = Modifier,
    title: @Composable (ColumnScope.() -> Unit) = {},
    selectedItem: ToDoFilterType = ToDoFilterType.RECENT,
    onSelectedItemChanged: (ToDoFilterType) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        title = { title() },
        content = {
            LazyColumn(
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                itemsIndexed(
                    ToDoFilterType.entries,
                    key = { _, item -> item },
                ) { _, item ->
                    Text(
                        text = item.filter,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .applyFilterOnClick { onSelectedItemChanged(item) }
                                .background(
                                    color = if (item != selectedItem) BbangZipTheme.colors.staticWhite_FFFFFF else BbangZipTheme.colors.fillStrong_68645E_16,
                                    shape = RoundedCornerShape(16.dp),
                                )
                                .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevertCompleteBottomSheet(
    isBottomSheetVisible: Boolean,
    selectedItemPieceId: List<Int>,
    bottomSheetTitle: String,
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
                onClick = { onClickInteractButton(selectedItemPieceId[0]) },
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

@Preview(showBackground = true)
@Composable
fun TodoScreenMockPreview() {
    val mockToDoList =
        listOf(
            ToDoCardModel(
                pieceId = 1,
                subjectName = "Math",
                examName = "Algebra Exam",
                studyContents = "Review Chapters 1-3",
                startPage = 1,
                finishPage = 50,
                deadline = "2025-01-20",
                remainingDays = 2,
                cardState = BbangZipCardState.DEFAULT,
            ),
            ToDoCardModel(
                pieceId = 2,
                subjectName = "History",
                examName = "World War II Quiz",
                studyContents = "Notes on WWII",
                startPage = 5,
                finishPage = 30,
                deadline = "2025-01-22",
                remainingDays = 4,
                cardState = BbangZipCardState.COMPLETE,
            ),
            ToDoCardModel(
                pieceId = 3,
                subjectName = "Science",
                examName = "Physics Midterm",
                studyContents = "Kinematics and Dynamics",
                startPage = 20,
                finishPage = 80,
                deadline = "2025-01-25",
                remainingDays = 7,
                cardState = BbangZipCardState.CHECKED,
            ),
            ToDoCardModel(
                pieceId = 4,
                subjectName = "Art",
                examName = "Painting Project",
                studyContents = "Complete abstract art piece",
                startPage = 0,
                finishPage = 0,
                deadline = "2025-01-28",
                remainingDays = 10,
                cardState = BbangZipCardState.CHECKABLE,
            ),
            ToDoCardModel(
                pieceId = 2,
                subjectName = "History",
                examName = "World War II Quiz",
                studyContents = "Notes on WWII",
                startPage = 5,
                finishPage = 30,
                deadline = "2025-01-22",
                remainingDays = 4,
                cardState = BbangZipCardState.COMPLETE,
            ),
            ToDoCardModel(
                pieceId = 3,
                subjectName = "Science",
                examName = "Physics Midterm",
                studyContents = "Kinematics and Dynamics",
                startPage = 20,
                finishPage = 80,
                deadline = "2025-01-25",
                remainingDays = 7,
                cardState = BbangZipCardState.CHECKED,
            ),
            ToDoCardModel(
                pieceId = 4,
                subjectName = "Art",
                examName = "Painting Project",
                studyContents = "Complete abstract art piece",
                startPage = 0,
                finishPage = 0,
                deadline = "2025-01-28",
                remainingDays = 10,
                cardState = BbangZipCardState.CHECKABLE,
            ),
            ToDoCardModel(
                pieceId = 2,
                subjectName = "History",
                examName = "World War II Quiz",
                studyContents = "Notes on WWII",
                startPage = 5,
                finishPage = 30,
                deadline = "2025-01-22",
                remainingDays = 4,
                cardState = BbangZipCardState.COMPLETE,
            ),
            ToDoCardModel(
                pieceId = 3,
                subjectName = "Science",
                examName = "Physics Midterm",
                studyContents = "Kinematics and Dynamics",
                startPage = 20,
                finishPage = 80,
                deadline = "2025-01-25",
                remainingDays = 7,
                cardState = BbangZipCardState.CHECKED,
            ),
            ToDoCardModel(
                pieceId = 4,
                subjectName = "Art",
                examName = "Painting Project",
                studyContents = "Complete abstract art piece",
                startPage = 0,
                finishPage = 0,
                deadline = "2025-01-28",
                remainingDays = 10,
                cardState = BbangZipCardState.CHECKABLE,
            ),
            ToDoCardModel(
                pieceId = 2,
                subjectName = "History",
                examName = "World War II Quiz",
                studyContents = "Notes on WWII",
                startPage = 5,
                finishPage = 30,
                deadline = "2025-01-22",
                remainingDays = 4,
                cardState = BbangZipCardState.COMPLETE,
            ),
            ToDoCardModel(
                pieceId = 3,
                subjectName = "Science",
                examName = "Physics Midterm",
                studyContents = "Kinematics and Dynamics",
                startPage = 20,
                finishPage = 80,
                deadline = "2025-01-25",
                remainingDays = 7,
                cardState = BbangZipCardState.CHECKED,
            ),
            ToDoCardModel(
                pieceId = 4,
                subjectName = "Art",
                examName = "Painting Project",
                studyContents = "Complete abstract art piece",
                startPage = 0,
                finishPage = 0,
                deadline = "2025-01-28",
                remainingDays = 10,
                cardState = BbangZipCardState.CHECKABLE,
            ),
        )

    val mockTodoStates =
        listOf(
            TodoContract.TodoState(
                todoList = mockToDoList,
                pendingCount = 2,
                remainingStudyCount = 1,
                completeCount = 1,
                screenType = ToDoScreenType.DEFAULT,
            ),
            TodoContract.TodoState(
                todoList = mockToDoList,
                pendingCount = 1,
                remainingStudyCount = 0,
                completeCount = 3,
                screenType = ToDoScreenType.DELETE,
            ),
            TodoContract.TodoState(
                todoList = mockToDoList,
                pendingCount = 0,
                remainingStudyCount = 2,
                completeCount = 2,
                screenType = ToDoScreenType.EMPTY,
            ),
        )

    TodoScreen(
        todoState = mockTodoStates[0],
        todayDate = listOf("2025", "01", "18"),
        bottomPadding = PaddingValues()
    )
}
