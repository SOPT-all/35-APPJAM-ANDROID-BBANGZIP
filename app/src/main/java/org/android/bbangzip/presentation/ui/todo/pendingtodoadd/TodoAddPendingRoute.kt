package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.presentation.component.indicator.BbangZipLoadingIndicator

@Composable
fun TodoAddPendingRoute(
    snackbarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit = {},
    navigateToBack: () -> Unit = {},
    viewModel: TodoAddPendingViewModel = hiltViewModel(),
) {
    val todoAddPendingState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val todoAddsSnackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToBack ->
                    navigateToBack()

                TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToToDo ->
                    navigateToToDo()

                is TodoAddPendingContract.TodoAddPendingSideEffect.ShowSnackbar -> {
                    val job =
                        launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(context.getString(effect.message))
                        }
                    delay(2000)
                    job.cancel()
                }

                is TodoAddPendingContract.TodoAddPendingSideEffect.ShowTodoAddSnackbar -> {
                    val job =
                        launch {
                            todoAddsSnackbarHostState.currentSnackbarData?.dismiss()
                            todoAddsSnackbarHostState.showSnackbar(context.getString(effect.message, effect.formatArg))
                        }
                    delay(3000)
                    job.cancel()
                }
            }
        }
    }

    when (success) {
        true ->
            TodoAddPendingScreen(
                todoAddState = todoAddPendingState,
                todoAddSnackbarHostState = todoAddsSnackbarHostState,
                onBackIconClick = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnBackIconClick)
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetDismissRequest)
                },
                onFilterIconClick = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterIconClick)
                },
                onFilterBottomSheetItemClick = { filter ->
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetItemClick(selectedFilterItem = filter))
                },
                onItemPlusBtnClick = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnItemPlusBtnClick)
                },
                onToDoCardClick = { pieceId, cardState ->
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnToDoCardClick(pieceId = pieceId, cardState = cardState))
                },
            )

        false ->
            BbangZipLoadingIndicator()
    }
}
