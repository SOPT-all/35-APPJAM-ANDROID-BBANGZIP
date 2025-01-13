package org.android.bbangzip.presentation.component.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.type.BbangZipLevelType
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipLevelProgressBar(
    modifier: Modifier = Modifier,
    level: Int,
    currentPoint: Int
) {
    val levelType = BbangZipLevelType.entries[level - 1]

    Column(
        modifier = modifier
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                BbangZipChip(
                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                    text = stringResource(R.string.progressbar_chip_level, level.toString())
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = levelType.bbangZipName,
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119
                )
            }

            BbangZipPoint(
                currentPoint = currentPoint.toString(),
                totalPoint = levelType.bbangZipLevelPoint.toString()
            )
        }

        Spacer(Modifier.height(4.dp))

        BbangZipBasicProgressBar(
            progress = currentPoint.toFloat() / levelType.bbangZipLevelPoint.toFloat()
        )
    }
}

@Composable
private fun BbangZipPoint(
    modifier: Modifier = Modifier,
    currentPoint: String,
    totalPoint: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_trophy_default_24),
            contentDescription = null,
            tint = BbangZipTheme.colors.labelAlternative_282119_61
        )

        Text(
            text = stringResource(R.string.bbangzip_level_point, currentPoint, totalPoint),
            modifier = Modifier.padding(start = 1.dp),
            style = BbangZipTheme.typography.label2Medium,
            color = BbangZipTheme.colors.labelAlternative_282119_61
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BbangZipLevelProgressBarPreview() {
    BBANGZIPTheme {
        Column (
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize()
        ){
            BbangZipLevelProgressBar(
                level = 1,
                currentPoint = 50
            )
        }
    }
}