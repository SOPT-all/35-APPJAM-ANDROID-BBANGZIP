package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.model.SplitStudyData

fun NavController.navigateSubject() {
    navigate(
        route = BottomNavigationRoute.Subject,
    )
}

fun NavGraphBuilder.subjectNavGraph(
    navigateAddStudy: (SplitStudyData) -> Unit,
    navigateToSubjectDetail: (Int, String) -> Unit,
    navigateToAddSubject: () -> Unit,
    padding: PaddingValues,
) {
    composable<BottomNavigationRoute.Subject> {
        SubjectRoute(
            navigateAddStudy = navigateAddStudy,
            navigateToSubjectDetail = navigateToSubjectDetail,
            navigateToAddSubject = navigateToAddSubject,
            padding = padding,
        )
    }
}
