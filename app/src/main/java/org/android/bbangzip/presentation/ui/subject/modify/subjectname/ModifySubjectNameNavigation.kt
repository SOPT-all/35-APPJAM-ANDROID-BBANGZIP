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

fun NavController.navigateModifySubjectName(
) {
    navigate(
        route = ModifySubjectNameRoute,
    )
}

fun NavGraphBuilder.modifySubjectNameNavGraph(
    navigateToSubjectDetail: () -> Unit
) {
    composable<ModifySubjectNameRoute> {
        ModifySubjectNameRoute(
            navigateToSubjectDetail = navigateToSubjectDetail
        )
    }
}

@Serializable
object ModifySubjectNameRoute