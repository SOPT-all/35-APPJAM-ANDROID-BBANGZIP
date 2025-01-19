package org.android.bbangzip.presentation.ui.onboarding.onboardingstart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.ui.onboarding.OnboardingContract
import org.android.bbangzip.presentation.ui.onboarding.OnboardingViewModel

@Composable
fun OnboardingStartRoute(
    navigateToOnboarding: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is OnboardingContract.OnboardingSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                else -> Unit
            }
        }
    }

    OnboardingStartScreen(
        onClickNextBtn = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickNextBtn) }
    )
}
