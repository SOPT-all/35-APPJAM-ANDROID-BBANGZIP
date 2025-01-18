package org.android.bbangzip.presentation.ui.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.balloon.TopTailBalloon
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.ToDoCard
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun TodoScreen(
    todoState: TodoContract.TodoState,
    todayDate: List<String>,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onAddPendingStudyButtonClicked: () -> Unit = {},
    onAddStudyButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetDismissButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetApproveButtonClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
    onRevertCompleteBottomSheetDismissRequest: () -> Unit = {},
    onFilterIconClicked: () -> Unit = {},
    onFilterBottomSheetItemClicked: (Int) -> Unit = {},
    onFilterBottomSheetDismissRequest: () -> Unit = {},
    onDeleteIconClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onItemDeleteButtonClicked: () -> Unit = {},
    onDeleteScreenCardClicked: (Int) -> Unit = {},
    onFetchScreenCardClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = BbangZipTheme.colors.staticWhite_FFFFFF)
            .padding(bottom = 64.dp)
    ) {
        item {
            DateMessageCard(
                todayDate = todayDate,
                pendingCount = todoState.pendingCount,
                onAddPendingStudyButtonClicked = onAddPendingStudyButtonClicked
            )
        }

        item {
            Spacer(Modifier.height(48.dp))
        }

        item {
            StudyCountText(
                remainingCount = todoState.pendingCount,
                completeCount = todoState.completeCount,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
        }

        item {
            DeleteAndFilterIcons(
                onDeleteIconClicked = onDeleteIconClicked,
                onFilterIconClicked = onFilterIconClicked,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(Modifier.height(18.dp))
        }

        items(
            count = todoState.todoList.size,
            key = { index ->
                todoState.todoList[index].pieceId
            }) { index ->
            ToDoCard(
                data = todoState.todoList[index],
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
                onClick = {
                    when {
                        (todoState.screenType == ToDoScreenType.FETCH) && (todoState.todoList[index].cardState == BbangZipCardState.DEFAULT) ->
                            onFetchScreenCardClicked(
                                todoState.todoList[index].pieceId,
                                BbangZipCardState.COMPLETE
                            )

                        (todoState.screenType == ToDoScreenType.FETCH) && (todoState.todoList[index].cardState == BbangZipCardState.COMPLETE) ->
                            onFetchScreenCardClicked(
                                todoState.todoList[index].pieceId,
                                BbangZipCardState.DEFAULT
                            )

                        (todoState.screenType == ToDoScreenType.DELETE) && (todoState.todoList[index].cardState == BbangZipCardState.CHECKED) ->
                            onFetchScreenCardClicked(
                                todoState.todoList[index].pieceId,
                                BbangZipCardState.CHECKABLE
                            )

                        else ->
                            onFetchScreenCardClicked(
                                todoState.todoList[index].pieceId,
                                BbangZipCardState.CHECKED
                            )
                    }
                }
            )
        }
    }
}

@Composable
fun DateMessageCard(
    todayDate: List<String>,
    pendingCount: Int,
    onAddPendingStudyButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0, shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * (172f / 764f))
                    .padding(start = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = todayDate[0].toInt().toString(),
                        style = BbangZipTheme.typography.title3Bold,
                        color = BbangZipTheme.colors.labelNormal_282119
                    )

                    Text(
                        text = stringResource(R.string.todo_month_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = todayDate[1].toInt().toString(),
                        style = BbangZipTheme.typography.title3Bold,
                        color = BbangZipTheme.colors.labelNormal_282119
                    )

                    Text(
                        text = stringResource(R.string.todo_day_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = todayDate[2],
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119
                    )
                }

                Spacer(modifier = modifier.height(8.dp))

                if (pendingCount == 0) {
                    Text(
                        text = stringResource(R.string.todo_pending_count_text),
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61
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
                    tint = BbangZipTheme.colors.labelNormal_282119
                )

                Spacer(modifier = Modifier.width(6.dp))
            },
            horizontalPadding = 16.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun StudyCountText(
    remainingCount: Int,
    completeCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Text(
            text = when {
                completeCount > 0 -> stringResource(R.string.todo_complete_count_text, completeCount)
                remainingCount == 0 -> stringResource(R.string.todo_complete_remaining_nothing_text)
                else -> stringResource(R.string.todo_complete_nothing_text)
            },
            style = BbangZipTheme.typography.label1Bold,
            color = BbangZipTheme.colors.labelAlternative_282119_61
        )

        Text(
            text = if (remainingCount != 0) stringResource(R.string.todo_remaing_count_text, remainingCount)
            else stringResource(R.string.todo_complete_nothing_text),
            style = BbangZipTheme.typography.title3Bold,
            color = BbangZipTheme.colors.labelNormal_282119
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
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDeleteIconClicked() }
            , contentAlignment = Alignment.Center
            ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_trash_default_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61
            )
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDeleteIconClicked() }
            , contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter_default_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = BbangZipTheme.colors.labelAlternative_282119_61

            )
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//private fun TodoScreenPreview() {
//    Column(Modifier.fillMaxSize()) {
//        DateMessageCard(
//            todayDate = listOf("1", "2", "월"),
//            pendingCount = 1,
//            onAddPendingStudyButtonClicked = {}
//        )
//        Spacer(Modifier.height(16.dp))
//        StudyCountText(
//            1, 0,
//            Modifier.padding(horizontal = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        DeleteAndFilterIcons(Modifier.padding(horizontal = 16.dp))
//
//        Spacer(Modifier.height(24.dp))
//
//        LazyColumn {
//
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun TodoScreenMockPreview() {
    val mockToDoList = listOf(
        ToDoCardModel(
            pieceId = 1,
            subjectName = "Math",
            examName = "Algebra Exam",
            studyContents = "Review Chapters 1-3",
            startPage = 1,
            finishPage = 50,
            deadline = "2025-01-20",
            remainingDays = 2,
            cardState = BbangZipCardState.DEFAULT
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
            cardState = BbangZipCardState.COMPLETE
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
            cardState = BbangZipCardState.CHECKED
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
            cardState = BbangZipCardState.CHECKABLE
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
            cardState = BbangZipCardState.COMPLETE
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
            cardState = BbangZipCardState.CHECKED
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
            cardState = BbangZipCardState.CHECKABLE
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
            cardState = BbangZipCardState.COMPLETE
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
            cardState = BbangZipCardState.CHECKED
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
            cardState = BbangZipCardState.CHECKABLE
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
            cardState = BbangZipCardState.COMPLETE
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
            cardState = BbangZipCardState.CHECKED
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
            cardState = BbangZipCardState.CHECKABLE
        )
    )

    val mockTodoStates = listOf(
        TodoContract.TodoState(
            todoList = mockToDoList,
            pendingCount = 2,
            remainingStudyCount = 1,
            completeCount = 1,
            screenType = ToDoScreenType.FETCH
        ),
        TodoContract.TodoState(
            todoList = mockToDoList,
            pendingCount = 1,
            remainingStudyCount = 0,
            completeCount = 3,
            screenType = ToDoScreenType.DELETE
        ),
        TodoContract.TodoState(
            todoList = mockToDoList,
            pendingCount = 0,
            remainingStudyCount = 2,
            completeCount = 2,
            screenType = ToDoScreenType.EMPTY
        )
    )

    TodoScreen(
        todoState = mockTodoStates[0],
        todayDate = listOf("2025", "01", "18"),
        snackBarHostState = SnackbarHostState()
    )

}