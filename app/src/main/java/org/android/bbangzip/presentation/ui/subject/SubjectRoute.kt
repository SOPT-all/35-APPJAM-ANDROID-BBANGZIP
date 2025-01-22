package org.android.bbangzip.presentation.ui.subject

import androidx.compose.runtime.Composable
import org.android.bbangzip.presentation.model.SplitStudyData

@Composable
fun SubjectRoute(
    navigateAddStudy: (SplitStudyData) -> Unit,
) {
    SubjectScreen(
        navigateAddStudy = navigateAddStudy
    )
}
