package org.android.bbangzip.presentation.ui.my

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyScreen(
    onClickLogoutBtn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "로그아웃",
        modifier = Modifier.clickable { onClickLogoutBtn() }
    )
}
