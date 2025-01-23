package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.ui.subject.addstudy.AddStudyRoute
import org.android.bbangzip.presentation.ui.subject.addstudy.SplitStudyDataType
import kotlin.reflect.typeOf

fun NavController.navigateToModifySubjectName(
    subjectId: Int,
    subjectName: String,
) {
    navigate(
        route = ModifySubjectNameRoute(
            subjectId = subjectId,
            subjectName = subjectName
        ),
    )
}

fun NavGraphBuilder.modifySubjectNameNavGraph(
    navigateToSubjectDetail: (Int, String) -> Unit
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