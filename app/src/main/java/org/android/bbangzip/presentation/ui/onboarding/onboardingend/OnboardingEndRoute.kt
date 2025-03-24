package org.android.bbangzip.presentation.ui.onboarding.onboardingend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnboardingEndRoute(
    navigateToBack: () -> Unit,
    navigateToSubject: () -> Unit,
    viewModel: OnboardingEndViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is OnboardingEndContract.OnboardingEndSideEffect.NavigateToBack -> navigateToBack()
                is OnboardingEndContract.OnboardingEndSideEffect.NavigateToSubject -> navigateToSubject()
                else -> Unit
            }
        }
    }

    OnboardingEndScreen(
        onClickNextBtn = { viewModel.setEvent(OnboardingEndContract.OnboardingEndEvent.OnCompleteBtnClick) },
        onBackBtnClick = { viewModel.setEvent(OnboardingEndContract.OnboardingEndEvent.OnBackBtnClick) },
    )
}
