package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSubjectDetail(subjectId: Int) {
    navigate(
        route = SubjectDetailRoute(subjectId = subjectId),
    )
}

fun NavGraphBuilder.subjectDetailNavGraph(
    padding: PaddingValues,
    navigateToBack: () -> Unit = {},
    navigateToModifyMotivation: () -> Unit = {},
    navigateToModifySubjectName: (String) -> Unit = {},
) {
    composable<SubjectDetailRoute> { backStackEntry ->
        SubjectDetailRoute(
            padding = padding,
            subjectId = backStackEntry.toRoute<SubjectDetailRoute>().subjectId,
            navigateToBack = navigateToBack,
            navigateToModifyMotivation = navigateToModifyMotivation,
            navigateToModifySubjectName = navigateToModifySubjectName,
        )
    }
}

@Serializable
data class SubjectDetailRoute(val subjectId: Int)
