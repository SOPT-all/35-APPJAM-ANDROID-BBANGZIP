package org.android.bbangzip.presentation.ui.onboarding.onboardingend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.ui.onboarding.OnboardingContract
import org.android.bbangzip.presentation.ui.onboarding.OnboardingViewModel

@Composable
fun OnboardingEndRoute(
    popBackStack: () -> Unit,
    navigateToSubject: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is OnboardingContract.OnboardingSideEffect.PopBackStack -> popBackStack()
                is OnboardingContract.OnboardingSideEffect.NavigateToSubject -> navigateToSubject()
                else -> Unit
            }
        }
    }

    OnboardingEndScreen(
        onClickNextBtn = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickFinishBtn) },
        onBackBtnClick = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickBackFromEndBtn) },
    )
}
