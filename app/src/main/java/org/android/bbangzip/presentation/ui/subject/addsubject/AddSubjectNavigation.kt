package org.android.bbangzip.presentation.ui.subject.addsubject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateToAddSubject() {
    navigate(
        route = AddSubjectRoute,
    )
}

fun NavGraphBuilder.addSubjectNavGraph(
    navigateSubject: () -> Unit,
) {
    composable<AddSubjectRoute> {
        AddSubjectRoute(
            navigateSubjectDetail = navigateSubject,
        )
    }
}

@Serializable
object AddSubjectRoute
