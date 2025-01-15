package org.android.bbangzip.presentation.component.balloon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BottomTailBalloon(
    text: String,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    horizontalPadding: Dp = 0.dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
    ) {
        BalloonContainer(
            leadingIcon = leadingIcon,
            text = text,
            trailingIcon = trailingIcon,
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_balloon_tail_down),
            contentDescription = null,
            modifier = Modifier.padding(start = 24.dp),
            tint = BbangZipTheme.colors.staticWhite_FFFFFF,
        )
    }
}
