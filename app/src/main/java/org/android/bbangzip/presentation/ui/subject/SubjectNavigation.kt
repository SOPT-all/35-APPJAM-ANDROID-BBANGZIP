package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute

fun NavController.navigateSubject() {
    navigate(
        route = BottomNavigationRoute.Subject,
    )
}

fun NavGraphBuilder.subjectNavGraph(
    padding: PaddingValues,
) {
    composable<BottomNavigationRoute.Subject> {
        SubjectRoute(
            padding = padding,
        )
    }
}
