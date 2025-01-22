package org.android.bbangzip.presentation.ui.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.ui.my.MyRoute

fun NavController.navigateMy(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.My,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.myNavGraph(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    navigateToBbangZipDetail: () -> Unit
) {
    composable<BottomNavigationRoute.My> {
        MyRoute(
            padding = padding,
            navigateToLogin = navigateToLogin,
            navigateToBbangZipDetail = navigateToBbangZipDetail
        )
    }
}
