package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateAddStudy() {
    navigate(
        route = AddStudyRoute,
    )
}

fun NavGraphBuilder.addStudyNavGraph(
    padding: PaddingValues,
    navigateSplitStudy: (String) -> Unit,
) {
    composable<AddStudyRoute> {
        AddStudyRoute(
            padding = padding,
            navigateSplitStudy = navigateSplitStudy
        )
    }
}

@Serializable
object AddStudyRoute