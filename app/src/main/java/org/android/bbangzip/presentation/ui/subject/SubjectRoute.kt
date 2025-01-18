package org.android.bbangzip.presentation.ui.subject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectRoute(
    viewModel: SubjectViewModel = hiltViewModel(),
) {
    val subjectState by viewModel.uiState.collectAsStateWithLifecycle()

    SubjectScreen(
        onClickSelectable = {int -> viewModel.setEvent(SubjectContract.SubjectEvent.OnClickSelectableCard(int))},

        onClickTrashBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickTrashIcon) },
        onClickCancleBtn = { viewModel.setEvent(SubjectContract.SubjectEvent.OnClickCancleIcon) },
        subjects = subjectState.subjectList,
        cardViewType = subjectState.cardViewType,
    )
}
