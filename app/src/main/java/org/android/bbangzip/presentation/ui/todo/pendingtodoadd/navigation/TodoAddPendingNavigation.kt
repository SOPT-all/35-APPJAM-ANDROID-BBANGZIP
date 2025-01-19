package org.android.bbangzip.presentation.ui.todo.pendingtodoadd.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.todo.pendingtodoadd.TodoAddPendingRoute

@Serializable
object TodoAddPendingRoute

fun NavController.navigateTodoAddPending() {
    navigate(
        route = TodoAddPendingRoute,
    )
}

fun NavGraphBuilder.todoAddPendingNavGraph(
    snackBarHostState: SnackbarHostState,
    navigateToToDo: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<TodoAddPendingRoute> {
        TodoAddPendingRoute(
            snackBarHostState = snackBarHostState,
            navigateToToDo = navigateToToDo,
            navigateToBack = navigateToBack,
        )
    }
}
