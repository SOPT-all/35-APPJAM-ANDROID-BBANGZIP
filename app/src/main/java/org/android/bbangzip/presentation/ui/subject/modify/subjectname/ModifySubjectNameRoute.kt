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
    subjectId: Int,
    subjectName: String,
    navigateToBack: () -> Unit,
    navigateToSubjectDetail: (Int, String) -> Unit,
    viewModel: ModifySubjectNameViewModel = hiltViewModel(),
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

                is ModifySubjectNameContract.ModifySubjectNameSideEffect.NavigateToBack -> {
                    navigateToBack()
                }
            }
        }
    }

    ModifySubjectNameScreen(
        state = modifySubjectNameState,
        onSubjectNameChange = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnSubjectNameChange(it)) },
        onTextFieldFocusChange = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnTextFieldFocusChange(it)) },
        onModifyBtnClick = { id, name -> viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnModifyBtnClick(id, name)) },
        onTextFieldDeleteIconClick = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnTextFieldDeleteIconClick) },
        onBackIconClick = { viewModel.setEvent(ModifySubjectNameContract.ModifySubjectNameEvent.OnBackIconClick)}
    )
}
