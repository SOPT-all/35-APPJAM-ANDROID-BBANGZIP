package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun SubjectScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxSize().background(color = defaultBbangZipColors.backgroundNormal_FFFFFF)) {
        Text("subject 탭")
    }
}
