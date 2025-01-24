package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

@Composable
fun ModifySubjectNameRoute(
    viewModel: ModifySubjectNameViewModel = hiltViewModel(),
    subjectId: Int,
    subjectName: String,
    navigateToSubjectDetail: (Int, String) -> Unit,
) {
    val modifySubjectNameState by viewModel.uiState.collectAsStateWithLifecycle()
    val view = LocalView.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.staticWhite_FFFFFF.toArgb()

    LaunchedEffect(Unit) {
        Timber.tag("initnitnitnit").d("subjectId: $subjectId, subjectName: $subjectName")
        viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.Initialize(subjectId, subjectName))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is ModifySubjectNameContract.ModifySubjectNameSideEffect.NavigationSubjectDetail -> {
                    navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                }

                ModifySubjectNameContract.ModifySubjectNameSideEffect.RedundantSnackBar -> {
                }
                ModifySubjectNameContract.ModifySubjectNameSideEffect.ShowSnackBar -> {
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
        onSubjectNameChanged = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnChangeSubjectName(it)) },
        onTextFieldFocusChanged = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnFocusTextField(it)) },
        onModifyBtnClicked = { id, name -> viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickModifyBtn(id, name)) },
        onDeleteBtnClicked = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnClickDeleteBtn) },
    )
}
