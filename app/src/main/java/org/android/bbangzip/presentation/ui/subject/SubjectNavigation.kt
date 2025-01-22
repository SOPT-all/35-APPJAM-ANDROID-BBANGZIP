package org.android.bbangzip.presentation.ui.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.ui.subject.splitstudy.SplitStudyContract

fun NavController.navigateSubject() {
    navigate(
        route = BottomNavigationRoute.Subject,
    )
}

fun NavGraphBuilder.subjectNavGraph(
    navigateAddStudy: (SplitStudyData)-> Unit
) {
    composable<BottomNavigationRoute.Subject> {
        SubjectRoute(
            navigateAddStudy = navigateAddStudy
        )
    }
}