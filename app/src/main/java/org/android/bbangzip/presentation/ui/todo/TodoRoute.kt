package org.android.bbangzip.presentation.ui.todo

import android.app.Activity
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
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
    val view = LocalView.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

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
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddPendingStudyBtnClick)
                },
                onAddStudyButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnAddStudyBtnClick)
                },
                onRevertCompleteBottomSheetDismissButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissBtnClick)
                },
                onRevertCompleteBottomSheetApproveButtonClicked = { pieceId ->
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
                onFilterBottomSheetItemClicked = { selectedFilter ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetItemClick(selectedFilterItem = selectedFilter))
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest)
                },
                onDeleteIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteIconClick)
                },
                onCloseIconClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnCloseIconClick)
                },
                onItemDeleteButtonClicked = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnItemDeleteBtnClick)
                },
                onDeleteScreenCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDeleteScreenCardClick(pieceId = pieceId, cardState = cardState))
                },
                onDefaultScreenCardClicked = { pieceId, cardState ->
                    viewModel.setEvent(TodoContract.TodoEvent.OnDefaultScreenCardClick(pieceId = pieceId, cardState = cardState))
                },
                onClickBadgeCloseBtn = {
                    viewModel.setEvent(TodoContract.TodoEvent.OnGetBadgeBottomSheetCloseBtnClick)
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
