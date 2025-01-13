package org.android.bbangzip.presentation.component.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipBasicProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    backgroundColor: Color = BbangZipTheme.colors.fillStrong_68645E_16,
    progressColor: Color = BbangZipTheme.colors.labelNormal_282119,
    clipShape: Shape = RoundedCornerShape(4.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(Color.White)
            .height(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BbangZipProgressBarPreview() {
    Column(
        modifier = Modifier
            .background(color = Color.Gray)
            .fillMaxSize()
    ) {
        BbangZipBasicProgressBar(
            progress = 0.5f
        )
    }
}