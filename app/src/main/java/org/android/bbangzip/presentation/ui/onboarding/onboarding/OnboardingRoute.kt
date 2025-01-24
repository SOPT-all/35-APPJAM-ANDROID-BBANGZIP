package org.android.bbangzip.presentation.ui.onboarding.onboarding

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.ui.onboarding.OnboardingContract
import org.android.bbangzip.presentation.ui.onboarding.OnboardingViewModel
import timber.log.Timber

@Composable
fun OnboardingRoute(
    popBackStack: () -> Unit,
    navigateToOnboardingEnd: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { 3 })

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        Timber.tag("[온보딩]").d("isOnboardingDone 검사 ${state.currentPage}")
        viewModel.setEvent(OnboardingContract.OnboardingEvent.Initialize)
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is OnboardingContract.OnboardingSideEffect.PopBackStack -> popBackStack()
                is OnboardingContract.OnboardingSideEffect.NavigateToOnboardingEnd -> navigateToOnboardingEnd()
                else -> Unit
            }
        }
    }

    LaunchedEffect(state.currentPage) {
        Timber.tag("[온보딩] currentPage").d("${state.currentPage} + ${pagerState.currentPage}")
        if (pagerState.currentPage != state.currentPage) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    OnboardingScreen(
        state = state,
        pagerState = pagerState,
        onBackBtnClick = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickBackBtn) },
        onNextBtnClick = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickNextBtn) },
        onUserNameChanged = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnChangeUserName(it)) },
        onSemesterChanged = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnChangeSemester(it)) },
        onSubjectChanged = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnChangeSubject(it)) },
        onChangeUserNameFocused = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnChangeUserNameFocused(it)) },
        onChangeSubjectFocused = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnChangeSubjectFocused(it)) },
        clearUserName = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickDeleteUserName) },
        clearSubject = { viewModel.setEvent(OnboardingContract.OnboardingEvent.OnClickDeleteSubject) },
    )
}
