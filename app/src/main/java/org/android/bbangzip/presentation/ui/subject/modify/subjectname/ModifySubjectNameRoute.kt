package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.android.bbangzip.presentation.ui.ModifySubjectName.modify.ModifySubjectNamename.ModifySubjectNameViewModel

@Composable
fun ModifySubjectNameRoute(
    viewModel: ModifySubjectNameViewModel = hiltViewModel()
){
    val modifySubjectNameState by viewModel.uiState.collectAsStateWithLifecycle()

    ModifySubjectNameScreen(
        subjectName = modifySubjectNameState.subjectName,
        isButtonEnable = modifySubjectNameState.isButtonEnable,
        isTextFieldFocused = modifySubjectNameState.isTextFieldFocused,
        textFieldInputState = modifySubjectNameState.subjectNameTextFieldState,
        onSubjectNameChanged = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnChangeSubjectName(it))},
        onTextFieldFocusChanged = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnFocusTextField(it))},
        onModifyBtnClicked = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickModifyBtn)},
        onDeleteBtnClicked = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickDeleteBtn)}
    )
}