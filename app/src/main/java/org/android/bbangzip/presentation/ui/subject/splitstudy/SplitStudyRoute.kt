package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData

@Composable
fun SplitStudyRoute(
    viewModel: SplitStudyViewModel = hiltViewModel(),
    addStudyData: AddStudyData,
    navigateToBack: () -> Unit = {},
    navigateToAddStudy: (SplitStudyData) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(SplitStudyContract.SplitStudyEvent.Initialize(addStudyData = addStudyData))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is SplitStudyContract.SplitStudySideEffect.NavigateToAddStudy -> {
                    navigateToBack()
                    navigateToAddStudy(effect.splitStudyData)
                }
                is SplitStudyContract.SplitStudySideEffect.NavigateToBack -> {
                    navigateToBack()
                }
            }
        }
    }

    val splitStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    if (splitStudyState.isSuccess) {
        SplitStudyScreen(
            state = splitStudyState,
            onBackIconClick = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnBackIconClick(it))
            },
            onStartPageChange = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnStartPageChange(index, value))
            },
            onEndPageChange = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnEndPageChange(index, value))
            },
            onIsStartPageFocusedChange = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnIsStartPageFocusedChange(index, value))
            },
            onIsEndPageFocusedChange = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnIsEndPageFocusedChange(index, value))
            },
            onDatePickerClick = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnDatePickerClick(it))
            },
            onDatePickerBottomSheetDismissRequest = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnDatePickerBottomSheetDismissRequest)
            },
            onConfirmDateBtnClick = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnConfirmDateBtnClick)
            },
            onDeadlineChange = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnDeadlineChange(it))
            },
            onSaveBtnClick = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnSaveBtnClick(it))
            },
        )
    }
}
