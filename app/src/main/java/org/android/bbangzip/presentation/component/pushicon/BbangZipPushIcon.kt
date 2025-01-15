package org.android.bbangzip.presentation.component.pushicon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.BbangZipPushIconState
import org.android.bbangzip.presentation.model.getBackgroundColor
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipPushIcon(
    modifier: Modifier = Modifier,
    pushIconText: String,
    bbangZipPushIconState: BbangZipPushIconState = BbangZipPushIconState.Disable,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .background(color = bbangZipPushIconState.getBackgroundColor(), shape = CircleShape)
                .padding(horizontal = 6.dp, vertical = 3.dp),
    ) {
        Text(
            text = pushIconText,
            style = BbangZipTheme.typography.caption2Bold,
            color = BbangZipTheme.colors.staticWhite_FFFFFF,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BbangZipPushIconPreview() {
    BBANGZIPTheme {
        Row {
            BbangZipPushIcon(
                pushIconText = "1",
                bbangZipPushIconState = BbangZipPushIconState.Positive
            )

            BbangZipPushIcon(
                pushIconText = "2",
                bbangZipPushIconState = BbangZipPushIconState.Disable
            )
        }
    }
}