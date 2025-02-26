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
                onClickBbangZip = { viewModel.setEvent(MyContract.MyEvent.OnBbangZipClick) },
                onClickBadgeCount = { viewModel.setEvent(MyContract.MyEvent.OnMyBadgeCountClick) },
                onClickLogoutBtn = { viewModel.setEvent(MyContract.MyEvent.OnLogoutBtnClick) },
                onClickWithdrawBtn = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawBtnClick) },
                onClickLogoutConfirmBtn = { viewModel.setEvent(MyContract.MyEvent.OnLogoutConfirmBtnClick) },
                onClickWithdrawConfirmBtn = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawConfirmBtnClick) },
                onClickLogoutCancelBtn = { viewModel.setEvent(MyContract.MyEvent.OnLogoutCancelBtnClick) },
                onClickWithdrawCancelBtn = { viewModel.setEvent(MyContract.MyEvent.OnWithdrawCancelBtnClick) },
            )

        else -> {
            BbangZipLoadingIndicator()
        }
    }
}
