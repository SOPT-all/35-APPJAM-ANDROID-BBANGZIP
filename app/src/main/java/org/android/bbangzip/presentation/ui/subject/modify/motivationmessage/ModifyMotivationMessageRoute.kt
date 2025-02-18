package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import android.app.Activity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun ModifyMotivationMessageRoute(
    subjectId: Int,
    subjectName: String,
    viewModel: ModifyMotivationMessageViewModel = hiltViewModel(),
    navigateToSubjectDetail: (Int, String) -> Unit,
    navigateToBack: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    val modifyMotivationMessageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.Initialize(subjectId, subjectName))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.NavigateSubjectDetail -> {
                    navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                }
                is ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.NavigateToBack -> {
                    navigateToBack()
                }
                is ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.ShowSuccessModifyMotivationMessageSnackBar -> {
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

    ModifyMotivationMessageScreen(
        state = modifyMotivationMessageState,
        onMotivationMessageChange = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnMotivationMessageChange(it)) },
        onTextFieldFocusChange = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnTextFieldFocusChange(it)) },
        onModifyBtnClick = { id, name -> viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnModifyBtnClick(id, name)) },
        onTextFieldDeleteIconClick = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnTextFieldDeleteIconClick) },
        onBackIconClick = {viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnBackIconClick)}
    )
}