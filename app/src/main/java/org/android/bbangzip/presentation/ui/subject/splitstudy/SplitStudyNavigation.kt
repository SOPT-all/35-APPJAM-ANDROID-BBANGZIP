package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData
import timber.log.Timber
import kotlin.reflect.typeOf

fun NavController.navigateSplitStudy(
    addStudyData: AddStudyData,
) {
    navigate(
        route = SplitStudyRoute(addStudyData = addStudyData),
    )
}

fun NavGraphBuilder.splitStudyNavGraph(
    navigateToBack: () -> Unit,
    navigateToAddStudy: (SplitStudyData) -> Unit,
) {
    composable<SplitStudyRoute>(
        typeMap = mapOf(typeOf<AddStudyData>() to AddStudyDataType),
    ) {
        SplitStudyRoute(
            addStudyData = it.toRoute<SplitStudyRoute>().addStudyData,
            navigateToBack = navigateToBack,
            navigateToAddStudy = navigateToAddStudy,
        )
    }
}

@Serializable
data class SplitStudyRoute(val addStudyData: AddStudyData)

val AddStudyDataType =
    object : NavType<AddStudyData>(isNullableAllowed = false) {
        override fun get(
            bundle: Bundle,
            key: String,
        ): AddStudyData? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): AddStudyData {
            return Json.decodeFromString(value)
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: AddStudyData,
        ) {
            bundle.putString(key, Json.encodeToString(AddStudyData.serializer(), value))
        }

        override fun serializeAsValue(value: AddStudyData): String {
            return Json.encodeToString(AddStudyData.serializer(), value)
        }
    }
