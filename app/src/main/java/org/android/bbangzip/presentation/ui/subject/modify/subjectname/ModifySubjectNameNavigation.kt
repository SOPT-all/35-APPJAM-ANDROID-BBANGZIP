package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToModifySubjectName(
    subjectId: Int,
    subjectName: String,
) {
    navigate(
        route =
            ModifySubjectNameRoute(
                subjectId = subjectId,
                subjectName = subjectName,
            ),
    )
}

fun NavGraphBuilder.modifySubjectNameNavGraph(
    navigateToSubjectDetail: (Int, String) -> Unit,
) {
    composable<ModifySubjectNameRoute> {
        ModifySubjectNameRoute(
            navigateToSubjectDetail = navigateToSubjectDetail,
            subjectId = it.toRoute<ModifySubjectNameRoute>().subjectId,
            subjectName = it.toRoute<ModifySubjectNameRoute>().subjectName,
        )
    }
}

@Serializable
data class ModifySubjectNameRoute(val subjectId: Int, val subjectName: String)
