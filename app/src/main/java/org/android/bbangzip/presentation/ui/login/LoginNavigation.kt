package org.android.bbangzip.presentation.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateLogin() {
    navigate(
        route = LoginRoute
    )
}

fun NavGraphBuilder.loginNavGraph(
    navigateToSubject: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    composable<LoginRoute> {
        LoginRoute(
            navigateToSubject = navigateToSubject,
            navigateToOnboarding = navigateToOnboarding
        )
    }
}

@Serializable
object LoginRoute