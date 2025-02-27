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
    navigateToSubjectDetail: (Int, String) -> Unit = { _, _ -> },
    navigateToAddSubject: () -> Unit = {},
    viewModel: SubjectViewModel = hiltViewModel(),
) {
    val subjectState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = true)

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                SubjectContract.SubjectSideEffect.NavigateToAddSubject -> {
                    navigateToAddSubject()
                }
                is SubjectContract.SubjectSideEffect.NavigateToSubjectDetail -> navigateToSubjectDetail(effect.subjectId, effect.subjectName)
                SubjectContract.SubjectSideEffect.ShowSnackbar -> TODO()
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
                state = subjectState,
                onDeleteModeSubjectCardClick = { id, name -> viewModel.setEvent(SubjectContract.SubjectEvent.OnDeleteModeSubjectCardClick(id)) },
                onTrashIconClick = { viewModel.setEvent(SubjectContract.SubjectEvent.OnTrashIconClick) },
                onDefaultModeSubjectCardClick = { id, name -> viewModel.setEvent(SubjectContract.SubjectEvent.OnDefaultModeSubjectCardClick(id, name)) },
                onCancleIconClick = { viewModel.setEvent(SubjectContract.SubjectEvent.OnCancleIconClick) },
                onAddSubjectCardClick = { viewModel.setEvent(SubjectContract.SubjectEvent.OnAddSubjectCardClick) },
                onDeleteBtnClick = { viewModel.setEvent(SubjectContract.SubjectEvent.OnDeleteBtnClick) }
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
