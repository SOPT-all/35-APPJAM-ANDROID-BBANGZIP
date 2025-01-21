package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.model.Route

fun NavController.navigateSplitStudy(
    subjectTitle: String,
) {
    navigate(
        route = SplitStudyRoute(subjectTitle),
    )
}

fun NavGraphBuilder.splitStudyNavGraph() {
    composable<SplitStudyRoute> {
        SplitStudyRoute(
            subjectName = it.toRoute<SplitStudyRoute>().subjectTitle
        )
    }
}

@Serializable
class SplitStudyRoute(val subjectTitle: String)