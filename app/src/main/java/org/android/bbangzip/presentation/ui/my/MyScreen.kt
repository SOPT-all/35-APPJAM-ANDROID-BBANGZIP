package org.android.bbangzip.presentation.ui.my

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyScreen(
    onClickLogoutBtn: () -> Unit,
    onClickWithdrawBtn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        Text(
            text = "로그아웃",
            modifier = Modifier.clickable { onClickLogoutBtn() },
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "회원탈퇴",
            modifier = Modifier.clickable { onClickWithdrawBtn() },
        )
    }
}
