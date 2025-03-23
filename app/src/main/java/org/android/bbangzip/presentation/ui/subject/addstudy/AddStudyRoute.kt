package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData

@Composable
fun AddStudyRoute(
    snackbarHostState: SnackbarHostState,
    splitStudyData: SplitStudyData,
    viewModel: AddStudyViewModel = hiltViewModel(),
    navigateToBack: () -> Unit = {},
    navigateSplitStudy: (AddStudyData) -> Unit = {},
    navigateSubjectDetail: (Int, String) -> Unit = { _, _ -> },
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(AddStudyContract.AddStudyEvent.Initialize(splitStudyData = splitStudyData))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is AddStudyContract.AddStudySideEffect.NavigateToSplitStudy -> {
                    navigateSplitStudy(it.addStudyData)
                }

                is AddStudyContract.AddStudySideEffect.NavigateToBack -> navigateToBack()

                is AddStudyContract.AddStudySideEffect.NavigateToSubjectDetail -> navigateSubjectDetail(it.subjectId, it.subjectName)

                is AddStudyContract.AddStudySideEffect.ShowSnackBar -> {
                    val job =
                        launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(it.message)
                        }
                    delay(2000)
                    job.cancel()
                }
            }
        }
    }

    val addStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    if (addStudyState.isSuccess) {
        AddStudyScreen(
            state = addStudyState,
            onSelectedDateChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnSelectedDateChange(it)) },
            onStudyContentChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnStudyContentChange(it)) },
            onStudyContentFocusChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnStudyContentFocusChange(it)) },
            onStartPageChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnStartPageChange(it)) },
            onStartPageFocusChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnStartPageFocusChange(it)) },
            onEndPageChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnEndPageChange(it)) },
            onEndPageFocusChange = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnEndPageFocusChange(it)) },
            onSplitBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnSplitBtnClick) },
            onShowDatePickerBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnShowDatePickerBtnClick) },
            onPieceNumberClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnPieceNumberClick(it)) },
            onCancleIconClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnCancleIconClick) },
            onConfirmDateBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnConfirmDateBtnClick) },
            onReSplitBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnReSplitBtnClick(it)) },
            onAddStudyBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnAddStudyBtnClick) },
            onBackIconClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnBackIconClick) },
            onDirectEnrollBtnClick = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnDirectEnrollBtnClick) },
        )
    }
}
