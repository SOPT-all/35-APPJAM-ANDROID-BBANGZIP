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
        onSubjectNameChange = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnSubjectNameChange(it)) },
        onTextFieldFocusChange = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnTextFieldFocus(it)) },
        onAddBtnClick = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnAddBtnClick) },
        onDeleteBtnClick = { viewModel.setEvent(AddSubjectContract.AddSubjectEvent.OnDeleteBtnClick) },
    )
}
