package org.android.bbangzip.presentation.ui.subject.addsubject.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.subject.addsubject.AddSubjectRoute

fun NavController.navigateToAddSubject() {
    navigate(
        route = AddSubjectRoute,
    )
}

fun NavGraphBuilder.addSubjectNavGraph(
    navigateToBack: () -> Unit,
    navigateToSubject: () -> Unit,
) {
    composable<AddSubjectRoute> {
        AddSubjectRoute(
            navigateToBack = navigateToBack,
            navigateToSubject = navigateToSubject,
        )
    }
}

@Serializable
object AddSubjectRoute
