package org.android.bbangzip.presentation.ui.login

import android.content.Context
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.UserPreferences

@Composable
fun LoginRoute(
    navigateToSubject: () -> Unit,
    navigateToOnboarding: () -> Unit,
    context: Context,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { state.onBoardingList.size })
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle(initialValue = UserPreferences.getDefaultInstance())

    LaunchedEffect(userPreferences) {
        if (userPreferences.isLogin) {
            if (state.isOnboardingCompleted) {
                navigateToOnboarding()
            } else {
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
        onClickKakaoLoginBtn = { viewModel.setEvent(LoginContract.LoginEvent.OnClickKakaoLoginBtn(context = context)) },
    )
}
