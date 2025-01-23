package org.android.bbangzip.presentation.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashRoute(
    navigateToLogin: () -> Unit,
    navigateToOnboardingStart: () -> Unit,
    navigateToSubject: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is SplashContract.SplashSideEffect.NavigateToSubject -> navigateToSubject()
                is SplashContract.SplashSideEffect.NavigateToLogin -> navigateToLogin()
                is SplashContract.SplashSideEffect.NavigateToOnboardingStart -> navigateToOnboardingStart()
            }
        }
    }

    SplashScreen()
}
