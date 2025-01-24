package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.model.SplitStudyData

fun NavController.navigateToSubjectDetail(
    subjectId: Int,
    subjectName: String,
    navOptions: NavOptions
) {
    navigate(
        route =
            SubjectDetailRoute(
                subjectId = subjectId,
                subjectName = subjectName,
            ),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.subjectDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit = {},
    navigateToModifyMotivation: (Int, String) -> Unit = { _, _ -> },
    navigateToModifySubjectName: (Int, String) -> Unit = { _, _ -> },
    navigateToSubject: () -> Unit = {},
    navigateToAddStudy: (SplitStudyData) -> Unit = {},
) {
    composable<SubjectDetailRoute> { backStackEntry ->
        SubjectDetailRoute(
            padding = padding,
            subjectId = backStackEntry.toRoute<SubjectDetailRoute>().subjectId,
            subjectName = backStackEntry.toRoute<SubjectDetailRoute>().subjectName,
            popBackStack = popBackStack,
            navigateToModifyMotivation = navigateToModifyMotivation,
            navigateToModifySubjectName = navigateToModifySubjectName,
            navigateToAddStudy = navigateToAddStudy,
        )
    }
}

@Serializable
data class SubjectDetailRoute(
    val subjectId: Int,
    val subjectName: String,
)
