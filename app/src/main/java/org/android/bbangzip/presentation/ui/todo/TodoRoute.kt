package org.android.bbangzip.presentation.ui.todo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TodoRoute(
    snackBarHostState: SnackbarHostState,
    navigateToAddToDo: () -> Unit = {},
    navigateToAddPendingToDo: () -> Unit = {},
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todoState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = true)
    val todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-E").withLocale(Locale.forLanguageTag("ko"))).split("-")

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoContract.TodoSideEffect.NavigateToAddPendingToDo ->
                    navigateToAddPendingToDo()

                TodoContract.TodoSideEffect.NavigateToAddToDo ->
                    navigateToAddToDo()

                is TodoContract.TodoSideEffect.ShowSnackBar ->
                    snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    when (success) {
        true ->
            TodoScreen(
                todoState = todoState,
                todayDate = todayDate,
                snackBarHostState = snackBarHostState,
                onAddPendingStudyButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddPendingStudyButtonClicked)
                },
                onAddStudyButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddStudyButtonClicked)
                },
                onRevertCompleteBottomSheetDismissButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissButtonClicked)
                },
                onRevertCompleteBottomSheetApproveButtonClicked = { pieceId ->
                    viewModel.setEvent(
                        TodoContract.TodoEvent.OnRevertCompleteBottomSheetApproveButtonClicked(pieceId = pieceId)
                    )
                },
                onRevertCompleteBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest)
                },
                onFilterIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterIconClicked)
                },
                onFilterBottomSheetItemClicked = { filterIndex ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetItemClicked(todoFilterItemIndex = filterIndex))
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest)
                },
                onDeleteIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteIconClicked)
                },
                onCloseIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnCloseIconClicked)
                },
                onItemDeleteButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnItemDeleteButtonClicked)
                },
                onDeleteScreenCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteScreenCardClicked(pieceId = pieceId, cardState = cardState))
                },
                onDefaultScreenCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDefaultScreenCardClicked(pieceId = pieceId, cardState = cardState))
                },
            )

        false -> Text("땡!")

    }
}
