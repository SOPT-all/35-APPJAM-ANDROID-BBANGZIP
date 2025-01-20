package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateAddStudy() {
    navigate(
        route = AddStudyRoute,
    )
}

fun NavGraphBuilder.addStudyNavGraph() {
    composable<AddStudyRoute> {
        AddStudyRoute()
    }
}

@Serializable
object AddStudyRoute