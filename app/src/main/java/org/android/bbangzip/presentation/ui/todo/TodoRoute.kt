package org.android.bbangzip.presentation.ui.todo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.presentation.component.indicator.BbangZipLoadingIndicator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TodoRoute(
    snackbarHostState: SnackbarHostState,
    bottomPadding: PaddingValues,
    navigateToAddToDo: () -> Unit = {},
    navigateToAddPendingToDo: () -> Unit = {},
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val todoState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val todayDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-E").withLocale(Locale.forLanguageTag("ko"))).split("-")
    val context = LocalContext.current

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                TodoContract.TodoSideEffect.NavigateToAddPendingToDo ->
                    navigateToAddPendingToDo()

                TodoContract.TodoSideEffect.NavigateToAddToDo ->
                    navigateToAddToDo()

                is TodoContract.TodoSideEffect.ShowSnackbar -> {
                    val job =
                        launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(context.getString(effect.message))
                        }
                    delay(2000)
                    job.cancel()
                }

                is TodoContract.TodoSideEffect.ShowFormattedSnackbar -> {
                    val job =
                        launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(context.getString(effect.message, effect.formatArg))
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
                onAddPendingStudyBtnClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddPendingStudyBtnClick)
                },
                onAddStudyBtnClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddStudyBtnClick)
                },
                onRevertCompleteBottomSheetDismissBtnClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissBtnClick)
                },
                onRevertCompleteBottomSheetApproveBtnClick = { pieceId ->
                    viewModel.setEvent(
                        TodoContract.TodoEvent.OnRevertCompleteBottomSheetApproveBtnClick(pieceId = pieceId),
                    )
                },
                onRevertCompleteBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissRequest)
                },
                onFilterIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterIconClick)
                },
                onFilterBottomSheetItemClick = { selectedFilter ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetItemClick(selectedFilterItem = selectedFilter))
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest)
                },
                onDeleteIconClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteIconClick)
                },
                onCloseIconClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnCloseIconClick)
                },
                onItemDeleteBtnClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnItemDeleteBtnClick)
                },
                onDeleteScreenCardClick = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteScreenCardClick(pieceId = pieceId, cardState = cardState))
                },
                onDefaultScreenCardClick = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDefaultScreenCardClick(pieceId = pieceId, cardState = cardState))
                },
                onBadgeCloseBtnClick = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnGetBadgeBottomSheetCloseBtnClick)
                },
            )

        false ->
            BbangZipLoadingIndicator()
    }
}
