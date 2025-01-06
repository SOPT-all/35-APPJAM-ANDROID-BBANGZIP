package org.android.bbangzip.presentation.ui.dummy.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.ui.dummy.DummyRoute


fun NavController.navigateDummy(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Dummy,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.dummyNavGraph() {
    composable<BottomNavigationRoute.Dummy> {
        DummyRoute(
            snackbarHostState = SnackbarHostState()
        )
    }
}