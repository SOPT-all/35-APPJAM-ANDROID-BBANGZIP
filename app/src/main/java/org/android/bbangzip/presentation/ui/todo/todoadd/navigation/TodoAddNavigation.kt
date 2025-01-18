package org.android.bbangzip.presentation.ui.todo.todoadd.navigation

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
    navigateToToDo: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<TodoAddRoute> {
        TodoAddRoute(
            navigateToToDo = navigateToToDo,
            navigateToBack = navigateToBack,
        )
    }
}