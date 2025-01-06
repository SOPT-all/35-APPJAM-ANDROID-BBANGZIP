package org.android.bbangzip.presentation.ui.subject

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.android.bbangzip.presentation.model.BottomNavigationRoute

fun NavController.navigateSubject(navOptions: NavOptions) {
    navigate(
        route = BottomNavigationRoute.Subject,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.subjectNavGraph() {
    composable<BottomNavigationRoute.Subject> {
        SubjectRoute()
    }
}
