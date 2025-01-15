package org.android.bbangzip.presentation.component.balloon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BalloonContainer(
    text: String,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .applyShadows(BbangZipShadowType.STRONG, shape = RoundedCornerShape(size = 20.dp))
                .fillMaxWidth()
                .background(
                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                    shape = RoundedCornerShape(size = 20.dp),
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            leadingIcon()

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = text,
                style = BbangZipTheme.typography.body1Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
            )

            Spacer(modifier = Modifier.width(6.dp))

            trailingIcon()
        }
    }
}
