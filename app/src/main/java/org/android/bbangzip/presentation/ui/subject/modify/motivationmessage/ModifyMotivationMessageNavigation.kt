package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateToModifyMotivationMessage(
    subjectId: Int,
    subjectName: String,
) {
    navigate(
        route = ModifyMotivationMessageRoute(subjectId, subjectName),
    )
}

fun NavGraphBuilder.modifyMotivationMessageNavGraph(
    navigateToSubjectDetail: (Int, String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    composable<ModifyMotivationMessageRoute> {
        ModifyMotivationMessageRoute(
            navigateToSubjectDetail = navigateToSubjectDetail,
            snackbarHostState = snackbarHostState,
        )
    }
}

@Serializable
data class ModifyMotivationMessageRoute(val subjectId: Int, val subjectName: String)
