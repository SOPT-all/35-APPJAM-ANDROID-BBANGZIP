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
    navigateToSubject: () -> Unit,
) {
    val addSubjectState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                AddSubjectContract.AddSubjectSideEffect.NavigateToSubject -> {
                    navigateToSubject()
                }

                is AddSubjectContract.AddSubjectSideEffect.ShowSnackBar -> {}
            }
        }
    }

    AddSubjectScreen(
        state = addSubjectState,
        onSubjectNameChanged = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnChangeSubjectName(it)) },
        onTextFieldFocusChanged = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnFocusTextField(it)) },
        onAddBtnClicked = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnClickAddBtn) },
        onDeleteBtnClicked = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnClickDeleteBtn) },
    )
}
