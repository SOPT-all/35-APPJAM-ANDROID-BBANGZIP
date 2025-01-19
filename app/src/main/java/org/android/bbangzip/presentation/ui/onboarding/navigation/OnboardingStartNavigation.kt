package org.android.bbangzip.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.onboarding.onboardingstart.OnboardingStartRoute

fun NavController.navigateOnboardingStart() {
    navigate(
        route = OnboardingStartRoute,
    )
}

fun NavGraphBuilder.onboardingStartNavGraph(
    navigateToOnboarding: () -> Unit,
) {
    composable<OnboardingStartRoute> {
        OnboardingStartRoute(
            navigateToOnboarding = navigateToOnboarding,
        )
    }
}

@Serializable
object OnboardingStartRoute
