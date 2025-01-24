package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ModifyMotivationMessageRoute(
    viewModel: ModifyMotivationMessageViewModel = hiltViewModel(),
    navigateToSubjectDetail: (Int, String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val modifyMotivationMessageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.NavigateSubjectDetail -> {
                    navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                }
                is ModifyMotivationMessageContract.ModifyMotivationMessageSideEffect.ShowSnackBar -> {
                    val job =
                        launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(effect.message)
                        }
                    delay(2000)
                    job.cancel()
                }
            }
        }
    }

    ModifyMotivationMessageScreen(
        motivationMessage = modifyMotivationMessageState.motivationMessage,
        isButtonEnable = modifyMotivationMessageState.isButtonEnable,
        isTextFieldFocused = modifyMotivationMessageState.isTextFieldFocused,
        textFieldInputState = modifyMotivationMessageState.motivationMessageTextFieldState,
        subjectId = modifyMotivationMessageState.subjectId,
        subjectName = modifyMotivationMessageState.subjectName,
        onMotivationMessageChanged = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnChangeMotivationMessage(it)) },
        onTextFieldFocusChanged = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnFocusTextField(it)) },
        onModifyBtnClicked = { id, name -> viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickModifyBtn(id, name)) },
        onDeleteBtnClicked = { viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickDeleteBtn) },
    )
}
