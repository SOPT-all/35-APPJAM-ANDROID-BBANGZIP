package org.android.bbangzip.presentation.component.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.component.BbangZipPushIcon
import org.android.bbangzip.presentation.model.BbangZipPushIconState
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun OnboardingProgressBar(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int = 3
) {
    val progress = when (currentPage) {
        0 -> 0.04f
        1 -> 0.5f
        2 -> 1f
        else -> 0f
    }

    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            (1..totalPages).forEach { index ->
                val state = if (index <= currentPage + 1) {
                    BbangZipPushIconState.Positive
                } else {
                    BbangZipPushIconState.Disable
                }

                BbangZipPushIcon(
                    pushIconText = index.toString(),
                    bbangZipPushIconState = state
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        BbangZipBasicProgressBar(
            progress = progress,
            backgroundColor = BbangZipTheme.colors.fillStrong_68645E_16
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingProgressBarPreview() {
    val testPages = listOf(0, 1, 2)

    Column {
        testPages.forEach { currentPage ->
            OnboardingProgressBar(
                currentPage = currentPage,
                totalPages = 3,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}