package org.android.bbangzip.presentation.ui.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.splash.SplashRoute

fun NavController.navigateSplash() {
    navigate(
        route = SplashRoute,
    )
}

fun NavGraphBuilder.splashNavGraph(
    navigateToLogin: () -> Unit,
    navigateToOnboardingStart: () -> Unit,
    navigateToSubject: () -> Unit,
) {
    composable<SplashRoute> {
        SplashRoute(
            navigateToLogin = navigateToLogin,
            navigateToOnboardingStart = navigateToOnboardingStart,
            navigateToSubject = navigateToSubject,
        )
    }
}

@Serializable
object SplashRoute
