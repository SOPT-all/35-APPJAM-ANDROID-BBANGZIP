package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
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

fun NavController.navigateAddStudy(
    splitStudyData: SplitStudyData,
) {
    navigate(
        route = AddStudyRoute(splitStudyData = splitStudyData),
    )
}

fun NavGraphBuilder.addStudyNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateSplitStudy: (AddStudyData) -> Unit,
) {
    composable<AddStudyRoute>(
        typeMap = mapOf(typeOf<SplitStudyData>() to SplitStudyDataType),
    ) {
        AddStudyRoute(
            padding = padding,
            popBackStack = popBackStack,
            navigateSplitStudy = navigateSplitStudy,
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
            Timber.d("[쪼개기] get -> $key")
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): SplitStudyData {
            Timber.d("[쪼개기] parse -> $value")
            return Json.decodeFromString(value)
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: SplitStudyData,
        ) {
            Timber.d("[쪼개기] put -> $value")
            bundle.putString(key, Json.encodeToString(SplitStudyData.serializer(), value))
        }

        override fun serializeAsValue(value: SplitStudyData): String {
            Timber.d("[쪼개기] serialize ->  $value")
            return Json.encodeToString(SplitStudyData.serializer(), value)
        }
    }
