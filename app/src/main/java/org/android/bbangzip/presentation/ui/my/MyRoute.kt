package org.android.bbangzip.presentation.ui.my

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyRoute(
    navigateToLogin: () -> Unit,
    viewModel: MyViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is MyContract.MySideEffect.NavigateToLogin -> navigateToLogin()
            }
        }
    }

    MyScreen(
        onClickLogoutBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickLogoutBtn) },
        onClickWithdrawBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickWithdrawBtn) },
    )
}
