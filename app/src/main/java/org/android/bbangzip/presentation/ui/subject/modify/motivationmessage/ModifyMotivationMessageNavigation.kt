package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateModifyMotivationMessage(
) {
    navigate(
        route = ModifyMotivationMessageRoute,
    )
}

fun NavGraphBuilder.modifyMotivationMessageNavGraph(
    navigateToSubjectDetail: () -> Unit
) {
    composable<ModifyMotivationMessageRoute>() {
        ModifyMotivationMessageRoute(
            navigateToSubjectDetail = navigateToSubjectDetail
        )
    }
}

@Serializable
object ModifyMotivationMessageRoute