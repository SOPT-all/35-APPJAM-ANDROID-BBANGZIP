package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Bundle
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData
import kotlin.reflect.typeOf

fun NavController.navigateToAddStudy(
    splitStudyData: SplitStudyData,
) {
    navigate(
        route = AddStudyRoute(splitStudyData = splitStudyData),
    )
}

fun NavGraphBuilder.addStudyNavGraph(
    snackbarHostState: SnackbarHostState,
    navigateToBack: () -> Unit,
    navigateToSplitStudy: (AddStudyData) -> Unit,
    navigateToSubjectDetail: (Int, String) -> Unit,
) {
    composable<AddStudyRoute>(
        typeMap = mapOf(typeOf<SplitStudyData>() to SplitStudyDataType),
    ) {
        AddStudyRoute(
            snackbarHostState = snackbarHostState,
            navigateToBack = navigateToBack,
            navigateSplitStudy = navigateToSplitStudy,
            navigateSubjectDetail = navigateToSubjectDetail,
            splitStudyData = it.toRoute<AddStudyRoute>().splitStudyData,
        )
    }
}

@Serializable
class AddStudyRoute(val splitStudyData: SplitStudyData)

val SplitStudyDataType =
    object : NavType<SplitStudyData>(isNullableAllowed = false) {
        override fun get(
            bundle: Bundle,
            key: String,
        ): SplitStudyData? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): SplitStudyData {
            return Json.decodeFromString(value)
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: SplitStudyData,
        ) {
            bundle.putString(key, Json.encodeToString(SplitStudyData.serializer(), value))
        }

        override fun serializeAsValue(value: SplitStudyData): String {
            return Json.encodeToString(SplitStudyData.serializer(), value)
        }
    }
