package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.ToDoCard
import org.android.bbangzip.presentation.component.snackbar.BbangZipSnackBarHost
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.ui.todo.BbangZipToDoFilterPickerBottomSheet
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun TodoAddPendingScreen(
    todoAddState: TodoAddPendingContract.TodoAddPendingState,
    todoAddSnackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    onFilterBottomSheetDismissRequest: () -> Unit,
    onFilterIconClicked: () -> Unit,
    onFilterBottomSheetItemClicked: (ToDoFilterType) -> Unit,
    onItemPlusButtonClicked: () -> Unit,
    onToDoCardClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
    ) {
        Column {
            val scrollState = rememberLazyListState()
            val isShadowed by remember {
                derivedStateOf {
                    scrollState.firstVisibleItemScrollOffset > 0
                }
            }
            BbangZipBaseTopBar(
                isShadowed = isShadowed,
                title = "",
                leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
                onLeadingIconClick = onBackIconClicked,
            )

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                state = scrollState,
            ) {
                item {
                    Text(text = stringResource(R.string.todo_pending_add_title1), style = BbangZipTheme.typography.body1Bold, color = BbangZipTheme.colors.labelAlternative_282119_61)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = stringResource(R.string.todo_pending_add_title2), style = BbangZipTheme.typography.title3Bold, color = BbangZipTheme.colors.labelNormal_282119)

                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "${todoAddState.selectedItemList.size} ", style = BbangZipTheme.typography.body2Bold, color = BbangZipTheme.colors.labelNormal_282119)
                        Text(text = stringResource(R.string.todo_add_count, todoAddState.todoList.size), style = BbangZipTheme.typography.body2Bold, color = BbangZipTheme.colors.labelAlternative_282119_61)

                        Spacer(modifier = Modifier.weight(1f))

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

                    Spacer(modifier = Modifier.height(18.dp))
                }

                items(
                    count = todoAddState.todoList.size,
                    key = { index ->
                        todoAddState.todoList[index].pieceId
                    },
                ) { index ->
                    ToDoCard(
                        data = todoAddState.todoList[index],
                        modifier = Modifier.padding(vertical = 6.dp),
                        onClick = {
                            if (todoAddState.todoList[index].cardState == BbangZipCardState.CHECKED) {
                                onToDoCardClicked(
                                    todoAddState.todoList[index].pieceId,
                                    BbangZipCardState.CHECKABLE,
                                )
                            } else {
                                (todoAddState.todoList[index].cardState == BbangZipCardState.CHECKABLE)
                            }
                            onToDoCardClicked(
                                todoAddState.todoList[index].pieceId,
                                BbangZipCardState.CHECKED,
                            )
                        },
                    )
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BbangZipSnackBarHost(snackBarHostState = todoAddSnackBarHostState)

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onItemPlusButtonClicked() },
                label = stringResource(R.string.todo_add_plus_button_label),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                isEnable = todoAddState.selectedItemList.isNotEmpty(),
                trailingIcon = R.drawable.ic_plus_thick_24,
            )
        }
    }
    BbangZipToDoFilterPickerBottomSheet(
        isBottomSheetVisible = todoAddState.todoFilterBottomSheetState,
        selectedItem = todoAddState.selectedFilter,
        onSelectedItemChanged = onFilterBottomSheetItemClicked,
        onDismissRequest = onFilterBottomSheetDismissRequest,
    )
}
