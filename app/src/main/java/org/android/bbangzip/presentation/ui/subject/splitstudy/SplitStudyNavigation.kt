package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateSplitStudy() {
    navigate(
        route = SplitStudyRoute,
    )
}

fun NavGraphBuilder.splitStudyNavGraph() {
    composable<SplitStudyRoute> {
        SplitStudyScreen()
    }
}

@Serializable
object SplitStudyRoute