package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectRoute(
    padding: PaddingValues,
    viewModel: SubjectViewModel = hiltViewModel(),
) {
    val subjectState by viewModel.uiState.collectAsStateWithLifecycle()

    SubjectScreen(
        padding = padding,
        onClickDeleteModeCard = { id -> viewModel.setEvent(SubjectContract.SubjectEvent.OnClickDeleteModeCard(id))},
        onClickTrashBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickTrashIcon) },
        onClickCancleBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickCancleIcon) },
        subjects = subjectState.subjectList,
        cardViewType = subjectState.cardViewType,
        deletedSet = subjectState.subjectSetToDelete
    )
}
