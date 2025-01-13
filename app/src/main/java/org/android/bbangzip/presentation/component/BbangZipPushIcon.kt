package org.android.bbangzip.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.BbangZipPushIconState
import org.android.bbangzip.presentation.model.getBackgroundColor
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipPushIcon(
    modifier: Modifier = Modifier,
    pushIconText: String,
    bbangZipPushIconState: BbangZipPushIconState = BbangZipPushIconState.Disable,
) {
    Text(
        text = pushIconText,
        modifier =
            modifier
                .background(color = bbangZipPushIconState.getBackgroundColor(), shape = CircleShape)
                .padding(horizontal = 7.dp, vertical = 3.dp),
        style = BbangZipTheme.typography.caption2Bold,
        color = BbangZipTheme.colors.staticWhite_FFFFFF,
    )
}
