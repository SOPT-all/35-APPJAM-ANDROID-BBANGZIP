package org.android.bbangzip.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.onboarding.onboarding.OnboardingRoute

fun NavController.navigateOnboarding() {
    navigate(
        route = OnboardingRoute,
    )
}

fun NavGraphBuilder.onboardingNavGraph(
    navigateToBack: () -> Unit,
    navigateToOnboardingEnd: () -> Unit,
) {
    composable<OnboardingRoute> {
        OnboardingRoute(
            navigateToBack = navigateToBack,
            navigateToOnboardingEnd = navigateToOnboardingEnd,
        )
    }
}

@Serializable
object OnboardingRoute
