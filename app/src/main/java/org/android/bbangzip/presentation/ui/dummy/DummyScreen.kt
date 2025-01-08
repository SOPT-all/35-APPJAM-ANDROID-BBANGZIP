package org.android.bbangzip.presentation.ui.dummy

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.android.bbangzip.presentation.ui.dummy.DummyContract.DummyState

@Composable
fun DummyScreen(
    dummyState: DummyState,
    onClickNextBtn: (String) -> Unit = {},
) {
    Text("안녕?")
}
