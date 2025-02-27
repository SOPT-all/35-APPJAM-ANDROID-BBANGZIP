package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.model.SplitStudyData

fun NavController.navigateSubject(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Subject,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.subjectNavGraph(
    navigateToSubjectDetail: (Int, String) -> Unit,
    navigateToAddSubject: () -> Unit,
    padding: PaddingValues,
) {
    composable<BottomNavigationRoute.Subject> {
        SubjectRoute(
            navigateToSubjectDetail = navigateToSubjectDetail,
            navigateToAddSubject = navigateToAddSubject,
            padding = padding,
        )
    }
}
