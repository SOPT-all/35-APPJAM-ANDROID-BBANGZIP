package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateSubjectDetail() {
    navigate(
        route = SubjectDetailRoute,
    )
}

fun NavGraphBuilder.subjectDetailNavGraph(
    padding: PaddingValues,
) {
    composable<SubjectDetailRoute> {
        SubjectDetailRoute(
            padding = padding,
        )
    }
}

@Serializable
object SubjectDetailRoute
