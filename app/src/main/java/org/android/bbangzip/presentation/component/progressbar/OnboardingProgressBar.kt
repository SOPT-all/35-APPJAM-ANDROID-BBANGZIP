package org.android.bbangzip.presentation.component.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.component.pushicon.BbangZipPushIcon
import org.android.bbangzip.presentation.model.BbangZipPushIconState
import org.android.bbangzip.presentation.type.OnboardingType
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun OnboardingProgressBar(
    onboardingType: OnboardingType,
    modifier: Modifier = Modifier,
) {
    val currentPage = onboardingType.ordinal + 1
    val progress =
        when (onboardingType) {
            OnboardingType.FIRST -> 0.04f
            OnboardingType.SECOND -> 0.5f
            OnboardingType.THIRD -> 1f
        }

    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OnboardingType.entries.forEachIndexed { index, _ ->
                val state =
                    if (index < currentPage) {
                        BbangZipPushIconState.Positive
                    } else {
                        BbangZipPushIconState.Disable
                    }

                BbangZipPushIcon(
                    pushIconText = (index + 1).toString(),
                    bbangZipPushIconState = state,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    BbangZipBasicProgressBar(
        modifier = modifier,
        progress = progress,
        backgroundColor = BbangZipTheme.colors.fillStrong_68645E_16,
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingProgressBarPreview() {
    val testPages = listOf(0, 1, 2)

    Column {
        testPages.forEach { currentPage ->
            OnboardingProgressBar(
                onboardingType = OnboardingType.entries[currentPage],
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
