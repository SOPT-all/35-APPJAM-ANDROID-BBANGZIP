package org.android.bbangzip.presentation.ui.subject.addsubject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddSubjectRoute(
    viewModel: AddSubjectViewModel = hiltViewModel(),
    navigateSubjectDetail: () -> Unit
){
    val addSubjectState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.uiSideEffect.collectLatest{ effect ->
            when(effect){
                AddSubjectContract.AddSubjectSideEffect.NavigateSubjectDetail -> {
                    navigateSubjectDetail()
                }
            }
        }
    }

    AddSubjectScreen(
        subjectName = addSubjectState.subjectName,
        isButtonEnable = addSubjectState.isButtonEnable,
        isTextFieldFocused = addSubjectState.isTextFieldFocused,
        textFieldInputState = addSubjectState.subjectTextFieldState,
        onSubjectNameChanged = {viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnChangeSubjectName(it))},
        onTextFieldFocusChanged = {viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnFocusTextField(it))},
        onAddBtnClicked = {viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnClickAddBtn)},
        onDeleteBtnClicked = {viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnClickDeleteBtn)}
    )
}