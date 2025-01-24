package org.android.bbangzip.presentation.ui.my.bbangzipdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.my.bbangzipdetail.BbangZipDetailRoute

fun NavController.navigateBbangZipDetail() {
    navigate(
        route = BbangZipDetailRoute,
    ) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.bbangZipDetailNavGraph(
    popBackStack: () -> Unit,
) {
    composable<BbangZipDetailRoute> {
        BbangZipDetailRoute(
            popBackStack = popBackStack,
        )
    }
}

@Serializable
object BbangZipDetailRoute
