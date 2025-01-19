package org.android.bbangzip.presentation.ui.todo.todoadd.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.todo.todoadd.TodoAddRoute

@Serializable
object TodoAddRoute

fun NavController.navigateTodoAdd() {
    navigate(
        route = TodoAddRoute,
    )
}

fun NavGraphBuilder.todoAddNavGraph(
    snackBarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<TodoAddRoute> {
        TodoAddRoute(
            snackBarHostState = snackBarHostState,
            navigateToToDo = navigateToToDo,
            navigateToBack = navigateToBack,
        )
    }
}
