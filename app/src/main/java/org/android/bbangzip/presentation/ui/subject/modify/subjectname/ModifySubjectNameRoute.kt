package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ModifySubjectNameRoute(
    viewModel: ModifySubjectNameViewModel = hiltViewModel(),
    subjectId: Int,
    subjectName: String,
    navigateToSubjectDetail: (Int, String) -> Unit,
){
    val modifySubjectNameState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.Initialize(subjectId, subjectName))
    }

    LaunchedEffect(viewModel.uiSideEffect){
        viewModel.uiSideEffect.collectLatest{ effect ->
            when(effect){
                is ModifySubjectNameContract.ModifySubjectNameSideEffect.NavigationSubjectDetail -> {
                    navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                }
            }
        }
    }

    ModifySubjectNameScreen(
        subjectName = modifySubjectNameState.subjectName,
        isButtonEnable = modifySubjectNameState.isButtonEnable,
        isTextFieldFocused = modifySubjectNameState.isTextFieldFocused,
        textFieldInputState = modifySubjectNameState.subjectNameTextFieldState,
        subjectId = modifySubjectNameState.subjectId,
        onSubjectNameChanged = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnChangeSubjectName(it))},
        onTextFieldFocusChanged = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnFocusTextField(it))},
        onModifyBtnClicked = {id, name ->viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickModifyBtn(id, name))},
        onDeleteBtnClicked = {viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickDeleteBtn)}
    )
}