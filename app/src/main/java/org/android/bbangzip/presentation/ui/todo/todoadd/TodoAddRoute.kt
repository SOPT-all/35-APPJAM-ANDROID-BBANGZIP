package org.android.bbangzip.presentation.ui.todo.todoadd

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun TodoAddRoute(
    snackbarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit = {},
    navigateToBack: () -> Unit = {},
    viewModel: TodoAddViewModel = hiltViewModel(),
) {
    val todoAddState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
    val todoAddsSnackBarHostState = remember { SnackbarHostState() }
    val view = LocalView.current
    val context = LocalContext.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.staticWhite_FFFFFF.toArgb()

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
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(context.getString(effect.message))
                        }
                    delay(2000)
                    job.cancel()
                }

                is TodoAddContract.TodoAddSideEffect.ShowTodoAddSnackBar -> {
                    val job =
                        launch {
                            todoAddsSnackBarHostState.currentSnackbarData?.dismiss()
                            todoAddsSnackBarHostState.showSnackbar(context.getString(effect.message, effect.formatArg))
                        }
                    delay(3000)
                    job.cancel()
                }
            }
        }
    }

    when (success) {
        true ->
            TodoAddScreen(
                todoAddState = todoAddState,
                todoAddSnackBarHostState = todoAddsSnackBarHostState,
                onBackIconClick = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnBackIconClick)
                },
                onFilterBottomSheetDismissRequest = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterBottomSheetDismissRequest)
                },
                onFilterIconClick = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterIconClick)
                },
                onFilterBottomSheetItemClick = { filter ->
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnFilterBottomSheetItemClick(selectedFilterItem = filter))
                },
                onItemPlusBtnClick = {
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnItemPlusBtnClick)
                },
                onToDoCardClick = { pieceId, cardState ->
                    viewModel.setEvent(TodoAddContract.TodoAddEvent.OnToDoCardClick(pieceId = pieceId, cardState = cardState))
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
