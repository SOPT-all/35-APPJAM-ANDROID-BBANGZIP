package org.android.bbangzip.presentation.ui.login

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.UserPreferences
import timber.log.Timber

@Composable
fun LoginRoute(
    navigateToSubject: () -> Unit,
    navigateToOnboarding: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { state.onBoardingList.size })
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle(initialValue = UserPreferences.getDefaultInstance())
    val context = LocalContext.current

    LaunchedEffect(userPreferences.isOnboardingCompleted) {
        Timber.d("[로그인] 로그인 여부 ${userPreferences.isLogin}, 온보딩 여부 ${userPreferences.isOnboardingCompleted}, 액세스 토큰 ${userPreferences.accessToken}")
        if (userPreferences.isLogin) {
            Timber.d("[로그인] isLogin true")
            if (userPreferences.isOnboardingCompleted) {
                Timber.d("[로그인] isLogin true, onboarding true")
                navigateToSubject()
            } else {
                Timber.d("[로그인] isLogin true, onboarding false")
                navigateToOnboarding()
            }
        }
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is LoginContract.LoginSideEffect.NavigateToSubject -> navigateToSubject()
                is LoginContract.LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
        }
    }

    LoginScreen(
        state = state,
        pagerState = pagerState,
        onClickKakaoLoginBtn = { viewModel.setEvent(LoginContract.LoginEvent.OnClickKakaoLoginBtn(context)) },
    )
}
