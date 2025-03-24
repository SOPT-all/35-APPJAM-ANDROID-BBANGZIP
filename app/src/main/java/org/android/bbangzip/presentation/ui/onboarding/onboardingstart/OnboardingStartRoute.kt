package org.android.bbangzip.presentation.ui.onboarding.onboardingstart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnboardingStartRoute(
    navigateToOnboarding: () -> Unit,
    viewModel: OnboardingStartViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is OnboardingStartContract.OnboardingStartSideEffect.NavigateToOnboarding -> navigateToOnboarding()
                else -> Unit
            }
        }
    }

    OnboardingStartScreen(
        onClickNextBtn = { viewModel.setEvent(OnboardingStartContract.OnboardingStartEvent.OnNextBtnClick) },
    )
}
