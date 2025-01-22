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
    viewModel: MyViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is MyContract.MySideEffect.NavigateToLogin -> navigateToLogin()
                is MyContract.MySideEffect.NavigateToBbangZipDetail -> navigateToBbangZipDetail()
            }
        }
    }

    when (success) {
        true ->
            MyScreen(
                padding = padding,
                state = state,
                onClickBbangZip = { viewModel.setEvent(MyContract.MyEvent.OnClickBbangZip) },
                onClickLogoutBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickLogoutBtn) },
                onClickWithdrawBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickWithdrawBtn) },
                onClickLogoutConfirmBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickLogoutConfirmBtn) },
                onClickWithdrawConfirmBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickWithdrawConfirmBtn) },
                onClickLogoutCancelBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickLogoutCancelBtn) },
                onClickWithdrawCancelBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickWithdrawCancelBtn) },
            )

        else -> {
            BbangZipLoadingIndicator()
        }
    }
}
