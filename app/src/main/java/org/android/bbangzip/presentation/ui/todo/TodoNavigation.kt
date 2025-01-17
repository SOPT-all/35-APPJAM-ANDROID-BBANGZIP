package org.android.bbangzip.presentation.ui.todo

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute

fun NavController.navigateTodo(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Todo,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.todoNavGraph(snackBarHostState: SnackbarHostState) {
    composable<BottomNavigationRoute.Todo> {
        TodoRoute(snackBarHostState = snackBarHostState)
    }
}
