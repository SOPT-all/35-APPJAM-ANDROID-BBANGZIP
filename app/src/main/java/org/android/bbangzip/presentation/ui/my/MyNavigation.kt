package org.android.bbangzip.presentation.ui.my

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute

fun NavController.navigateMy(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.My,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.myNavGraph() {
    composable<BottomNavigationRoute.My> {
        MyRoute()
    }
}
