package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun SubjectScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = defaultBbangZipColors.backgroundNormal_FFFFFF),
    ) {
        Text("subject 탭")
    }
}

@Preview
@Composable
private fun SubjectScreenPreview() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = defaultBbangZipColors.backgroundNormal_FFFFFF),
    ) {
        Text("subject 탭")
        Button(
            onClick = {
                coroutineScope.launch {
                    val job =
                        launch {
                            snackBarHostState.currentSnackbarData?.dismiss()
                            snackBarHostState.showSnackbar("안녕안녕")
                        }
                    delay(2000)
                    job.cancel()
                }
            },
        ) { Text("눌러") }
    }
}
// 위에 코드는 클릭마다 스낵바가뜨고 2초후에 사라지게 만든 코드입니다
