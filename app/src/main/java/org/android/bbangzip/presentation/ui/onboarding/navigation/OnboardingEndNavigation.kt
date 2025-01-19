package org.android.bbangzip.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.android.bbangzip.presentation.ui.onboarding.onboardingend.OnboardingEndRoute

fun NavController.navigateOnboardingEnd() {
    navigate(
        route = OnboardingEndRoute
    )
}

fun NavGraphBuilder.onboardingEndNavGraph(
    navigateToSubject: () -> Unit
) {
    composable<OnboardingEndRoute> {
        OnboardingEndRoute(
            navigateToSubject = navigateToSubject
        )
    }
}

@Serializable
object OnboardingEndRoute