package org.android.bbangzip.presentation.ui.todo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute
import org.android.bbangzip.presentation.ui.todo.TodoRoute

fun NavController.navigateTodo(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Todo,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.todoNavGraph(
    snackBarHostState: SnackbarHostState,
    bottomPadding: PaddingValues,
    navigateToAddToDo: () -> Unit,
    navigateToAddPendingToDo: () -> Unit = {},
) {
    composable<BottomNavigationRoute.Todo> {
        TodoRoute(
            bottomPadding = bottomPadding,
            snackBarHostState = snackBarHostState,
            navigateToAddToDo = navigateToAddToDo,
            navigateToAddPendingToDo = navigateToAddPendingToDo,
        )
    }
}
