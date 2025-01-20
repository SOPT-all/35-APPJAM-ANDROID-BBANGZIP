package org.android.bbangzip.presentation.ui.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.ui.theme.BbangZipTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TodoRoute(
    snackBarHostState: SnackbarHostState,
    bottomPadding: PaddingValues,
    navigateToAddToDo: () -> Unit = {},
    navigateToAddPendingToDo: () -> Unit = {},
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val todoState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-E").withLocale(Locale.forLanguageTag("ko"))).split("-")

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoContract.TodoSideEffect.NavigateToAddPendingToDo ->
                    navigateToAddPendingToDo()

                TodoContract.TodoSideEffect.NavigateToAddToDo ->
                    navigateToAddToDo()

                is TodoContract.TodoSideEffect.ShowSnackBar -> {
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

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.setEvent(TodoContract.TodoEvent.Initialize)
    }

    when (success) {
        true ->
            TodoScreen(
                todoState = todoState,
                todayDate = todayDate,
                bottomPadding = bottomPadding,
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
                        TodoContract.TodoEvent.OnRevertCompleteBottomSheetApproveButtonClicked(pieceId = pieceId),
                    )
                },
                onRevertCompleteBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissRequest)
                },
                onFilterIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterIconClicked)
                },
                onFilterBottomSheetItemClicked = { selectedFilter ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetItemClicked(selectedFilterItem = selectedFilter))
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

        false ->
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
            }
    }
}
