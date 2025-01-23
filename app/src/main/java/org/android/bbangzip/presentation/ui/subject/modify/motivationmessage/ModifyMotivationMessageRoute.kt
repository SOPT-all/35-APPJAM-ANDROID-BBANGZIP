package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ModifyMotivationMessageRoute(
    viewModel: ModifyMotivationMessageViewModel = hiltViewModel()
){
    val modifyMotivationMessageState by viewModel.uiState.collectAsStateWithLifecycle()

    ModifyMotivationMessageScreen(
        motivationMessage = modifyMotivationMessageState.motivationMessage,
        isButtonEnable = modifyMotivationMessageState.isButtonEnable,
        isTextFieldFocused = modifyMotivationMessageState.isTextFieldFocused,
        textFieldInputState = modifyMotivationMessageState.motivationMessageTextFieldState,
        onMotivationMessageChanged = {viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnChangeMotivationMessage(it))},
        onTextFieldFocusChanged = {viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnFocusTextField(it))},
        onModifyBtnClicked = {viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickModifyBtn)},
        onDeleteBtnClicked = {viewModel.setEvent(ModifyMotivationMessageContract.ModifyMotivationMessageEvent.OnClickDeleteBtn)}
    )
}