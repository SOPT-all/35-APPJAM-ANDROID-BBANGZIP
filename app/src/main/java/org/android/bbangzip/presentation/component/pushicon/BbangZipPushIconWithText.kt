package org.android.bbangzip.presentation.component.pushicon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipPushIconWithText(
    backgroundColor: Color,
    count: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .background(color = backgroundColor, shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 3.dp),
        ) {
            Text(
                text = count.toString(),
                style = BbangZipTheme.typography.caption2Bold,
                color = BbangZipTheme.colors.staticWhite_FFFFFF,
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = BbangZipTheme.typography.caption1Bold,
            color = BbangZipTheme.colors.labelAssistive_282119_28,
        )
    }
}
