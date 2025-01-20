package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TodoAddPendingRoute(
    snackBarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit = {},
    navigateToBack: () -> Unit = {},
    viewModel: TodoAddPendingViewModel = hiltViewModel(),
) {
    val todoAddPendingState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val todoAddsSnackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToBack ->
                    navigateToBack()

                TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToToDo ->
                    navigateToToDo()

                is TodoAddPendingContract.TodoAddPendingSideEffect.ShowSnackBar -> {
                    val job =
                        launch {
                            snackBarHostState.currentSnackbarData?.dismiss()
                            snackBarHostState.showSnackbar(effect.message)
                        }
                    delay(2000)
                    job.cancel()
                }

                is TodoAddPendingContract.TodoAddPendingSideEffect.ShowTodoAddSnackBar -> {
                    val job =
                        launch {
                            todoAddsSnackBarHostState.currentSnackbarData?.dismiss()
                            todoAddsSnackBarHostState.showSnackbar(effect.message)
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
                todoAddSnackBarHostState = todoAddsSnackBarHostState,
                onBackIconClicked = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnBackIconClicked)
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetDismissRequest)
                },
                onFilterIconClicked = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterIconClicked)
                },
                onFilterBottomSheetItemClicked = { filter ->
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetItemClicked(selectedFilterItem = filter))
                },
                onItemPlusButtonClicked = {
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnItemPlusButtonClicked)
                },
                onToDoCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoAddPendingContract.TodoAddPendingEvent.OnToDoCardClicked(pieceId = pieceId, cardState = cardState))
                },
            )

        false ->
            Text("땡!")
    }
}
