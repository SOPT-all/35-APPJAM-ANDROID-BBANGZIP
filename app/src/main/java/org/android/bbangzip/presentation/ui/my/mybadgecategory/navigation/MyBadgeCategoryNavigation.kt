package org.android.bbangzip.presentation.ui.my.mybadgecategory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.my.mybadgecategory.MyBadgeCategoryRoute

@Serializable
object MyBadgeCategoryRoute

fun NavController.navigateToMyBadgeCategory() {
    navigate(
        route = MyBadgeCategoryRoute,
    )
}

fun NavGraphBuilder.myBadgeCategoryNavGraph(
    popBackStack: () -> Unit,
) {
    composable<MyBadgeCategoryRoute> {
        MyBadgeCategoryRoute(
            popBackStack = popBackStack,
        )
    }
}
