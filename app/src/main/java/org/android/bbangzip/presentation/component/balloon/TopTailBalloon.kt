package org.android.bbangzip.presentation.component.balloon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun TopTailBalloon(
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
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_balloon_tail_up_8),
            contentDescription = null,
            modifier = Modifier.padding(start = 24.dp),
            tint = BbangZipTheme.colors.staticWhite_FFFFFF,
        )

        BalloonContainer(
            leadingIcon = leadingIcon,
            text = text,
            trailingIcon = trailingIcon,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBalloonPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = defaultBbangZipColors.backgroundAccent_FFDAA0),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        TopTailBalloon(
            text = "사장님의 과제 빵점 탈출을 위해서",
            horizontalPadding = 16.dp,
        )
    }
}
