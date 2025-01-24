package org.android.bbangzip.presentation.ui.subject

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SubjectRoute(
    padding: PaddingValues,
    navigateAddStudy: (SplitStudyData) -> Unit,
    navigateToSubjectDetail: (Int, String) -> Unit = { _, _ -> },
    navigateToAddSubject: () -> Unit = {},
    viewModel: SubjectViewModel = hiltViewModel(),
) {
    val subjectState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = true)

    val view = LocalView.current
    val activity = view.context as Activity

    activity.window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                SubjectContract.SubjectSideEffect.NavigateToAddStudy -> {}
                SubjectContract.SubjectSideEffect.NavigateToAddSubject -> {
                    navigateToAddSubject()
                }
                is SubjectContract.SubjectSideEffect.NavigateToSubjectDetail -> navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                SubjectContract.SubjectSideEffect.ShowDeleteSuccessSnackBar -> TODO()
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.setEvent(SubjectContract.SubjectEvent.Initialize)
    }

    when (success) {
        true ->
            SubjectScreen(
                padding = padding,
                onClickDeleteModeCard = { id, name -> viewModel.setEvent(SubjectContract.SubjectEvent.OnClickDeleteModeCard(id)) },
                onClickTrashBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickTrashIcon) },
                onClickStudyCard = { id, name -> viewModel.setEvent(SubjectContract.SubjectEvent.OnClickStudyCard(id, name)) },
                onClickCancleBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickCancleIcon) },
                onClickAddSubject = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickAddSubject) },
                onClickDeleteBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickDeleteButton) },
                subjects = subjectState.subjectList,
                cardViewType = subjectState.cardViewType,
                deletedSet = subjectState.subjectSetToDelete,
                navigateAddStudy = navigateAddStudy,
            )

        false ->
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
            }
    }
}
