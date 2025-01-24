package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
            subjectId = it.toRoute<ModifyMotivationMessageRoute>().subjectId,
            subjectName = it.toRoute<ModifyMotivationMessageRoute>().subjectName,
            navigateToSubjectDetail = navigateToSubjectDetail,
            snackbarHostState = snackbarHostState,
        )
    }
}

@Serializable
data class ModifyMotivationMessageRoute(val subjectId: Int, val subjectName: String)
