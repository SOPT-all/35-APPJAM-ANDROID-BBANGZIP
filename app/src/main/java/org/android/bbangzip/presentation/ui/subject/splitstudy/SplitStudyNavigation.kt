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
import timber.log.Timber
import kotlin.reflect.typeOf

fun NavController.navigateSplitStudy(
    addStudyData: AddStudyData,
) {
    navigate(
        route = SplitStudyRoute(addStudyData= addStudyData),
    )
}

fun NavGraphBuilder.splitStudyNavGraph() {
    composable<SplitStudyRoute>(
        typeMap = mapOf(typeOf<AddStudyData>() to AddStudyDataType),
    ) {
        SplitStudyRoute2(
            addStudyData = it.toRoute<SplitStudyRoute>().addStudyData
        )
    }
}

@Serializable
data class SplitStudyRoute(val addStudyData: AddStudyData)

val AddStudyDataType = object : NavType<AddStudyData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): AddStudyData? {
        Timber.d("[쪼개기] get -> $key")
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): AddStudyData {
        Timber.d("[쪼개기] parse -> $value")
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: AddStudyData) {
        Timber.d("[쪼개기] put -> $value")
        bundle.putString(key, Json.encodeToString(AddStudyData.serializer(), value))
    }

    override fun serializeAsValue(value: AddStudyData): String {
        Timber.d("[쪼개기] serialize ->  $value")
        return Json.encodeToString(AddStudyData.serializer(), value)
    }
}