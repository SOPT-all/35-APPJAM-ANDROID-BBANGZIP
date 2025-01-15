package org.android.bbangzip.presentation.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipChip(
    backgroundColor: Color,
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(11.dp))
                .padding(horizontal = 12.dp, vertical = 2.dp),
    ) {
        Text(
            text = text,
            style = BbangZipTheme.typography.caption1Medium,
            color = BbangZipTheme.colors.staticWhite_FFFFFF,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BbangZipChipPreview() {
    BBANGZIPTheme {
        Column(
            Modifier.padding(16.dp),
        ) {
            BbangZipChip(
                backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                text = "D-14",
            )

            Spacer(modifier = Modifier.height(8.dp))

            BbangZipChip(
                backgroundColor = BbangZipTheme.colors.statusDestructive_FF8345,
                text = "D-14",
            )

            Spacer(modifier = Modifier.height(8.dp))

            BbangZipChip(
                backgroundColor = BbangZipTheme.colors.statusCautionary_FFB84A,
                text = "D-14",
            )

            Spacer(modifier = Modifier.height(8.dp))

            BbangZipChip(
                backgroundColor = BbangZipTheme.colors.labelAlternative_282119_61,
                text = "D-14",
            )
        }
    }
}
