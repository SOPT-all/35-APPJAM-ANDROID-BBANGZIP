package org.android.bbangzip.presentation.ui.my

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.component.indicator.BbangZipLoadingIndicator

@Composable
fun MyRoute(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    navigateToBbangZipDetail: () -> Unit,
    navigateToBadgeDetail: () -> Unit,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(Unit) {
        viewModel.setEvent(MyContract.MyEvent.Initialize)
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is MyContract.MySideEffect.NavigateToLogin -> navigateToLogin()
                is MyContract.MySideEffect.NavigateToBbangZipDetail -> navigateToBbangZipDetail()
                is MyContract.MySideEffect.NavigateToBadgeDetail -> navigateToBadgeDetail()
            }
        }
    }

    when (success) {
        true ->
            MyScreen(
                padding = padding,
                state = state,
                onBbangZipClick = { viewModel.setEvent(MyContract.MyEvent.OnBbangZipClick) },
                onBadgeCountClick = { viewModel.setEvent(MyContract.MyEvent.OnMyBadgeCountClick) },
                onLogoutBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnLogoutBtnClick) },
                onWithdrawBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawBtnClick) },
                onLogoutConfirmBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnLogoutConfirmBtnClick) },
                onWithdrawConfirmBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawConfirmBtnClick) },
                onLogoutCancelBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnLogoutCancelBtnClick) },
                onWithdrawCancelBtnClick = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawCancelBtnClick) },
            )

        else -> {
            BbangZipLoadingIndicator()
        }
    }
}
