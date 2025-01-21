package org.android.bbangzip.presentation.ui.my

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {
    MyScreen(
        onClickLogoutBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickLogoutBtn) },
        onClickWithdrawBtn = { viewModel.setEvent(MyContract.MyEvent.OnClickWithdrawBtn) }
    )
}
