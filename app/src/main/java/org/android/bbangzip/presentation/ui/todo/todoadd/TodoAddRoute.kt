package org.android.bbangzip.presentation.ui.todo.todoadd

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TodoAddRoute(
    snackBarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit = {},
    navigateToBack: () -> Unit = {},
    viewModel: TodoAddViewModel = hiltViewModel()
) {
    val todoAddState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = true)

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoAddContract.TodoAddSideEffect.NavigateToBack ->
                    navigateToBack()

                TodoAddContract.TodoAddSideEffect.NavigateToToDo ->
                    navigateToToDo()

                is TodoAddContract.TodoAddSideEffect.ShowSnackBar -> {
                    val job =
                        launch {
                            snackBarHostState.currentSnackbarData?.dismiss()
                            snackBarHostState.showSnackbar(effect.message)
                        }
                    delay(2000)
                    job.cancel()
                }
            }
        }
    }

    when (success) {
        true ->
            TodoAddScreen(
                todoAddState = todoAddState,
                onBackIconClicked = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnBackIconClicked)
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterIconClicked)
                },
                nFilterIconClicked = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterBottomSheetDismissRequest)
                },
                onFilterBottomSheetItemClicked = { filter ->
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterBottomSheetItemClicked(selectedFilterItem = filter))
                },
                onItemPlusButtonClicked = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnItemPlusButtonClicked)
                },
                onToDoCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnToDoCardClicked(pieceId = pieceId, cardState = cardState))
                }
            )

        false ->
            Text("땡!")
    }
}
