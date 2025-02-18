package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ModifyMotivationMessageRoute(
    subjectId: Int,
    subjectName: String,
    navigateToSubjectDetail: (Int, String) -> Unit,
    navigateToBack: () -> Unit,
    snackBarHostState: SnackbarHostState,
    viewModel: ModifyMotivationMessageViewModel = hiltViewModel(),
) {
    val modifyMotivationMessageState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

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
                            snackBarHostState.showSnackbar(context.getString(effect.message))
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