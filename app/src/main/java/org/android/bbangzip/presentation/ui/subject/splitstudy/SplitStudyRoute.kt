package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@Composable
fun SplitStudyRoute(
    viewModel: SplitStudyViewModel = hiltViewModel(),
    subjectName: String = ""
){

    LaunchedEffect(Unit){
        viewModel.setEvent(SplitStudyContract.SplitStudyEvent.Initialize(subjectName = subjectName))
        Timber.d("split에 온값 : $subjectName")
    }

    val splitStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    SplitStudyScreen(
        pieceNumber = splitStudyState.pieceNumber,
        subjectName = splitStudyState.subjectName,
        startPage = splitStudyState.startPage,
        endPage = splitStudyState.endPage,
    )
}