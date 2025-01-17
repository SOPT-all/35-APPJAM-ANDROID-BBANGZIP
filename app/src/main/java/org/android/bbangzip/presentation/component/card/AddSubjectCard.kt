package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun AddSubjectCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val width = (LocalConfiguration.current.screenWidthDp - 48) / 2
    Column(
        modifier =
            modifier
                .border(
                    width = 2.dp,
                    color = BbangZipTheme.colors.lineAlternative_68645E_08,
                    shape = RoundedCornerShape(size = 24.dp),
                )
                .width(width = width.dp)
                .applyFilterOnClick(
                    baseColor = BbangZipTheme.colors.staticWhite_FFFFFF,
                    radius = 24.dp,
                    isDisabled = false
                ){ onClick() }
                .padding(vertical = 59.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 1.dp,
                    color = BbangZipTheme.colors.lineNormal_68645E_22,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus_default_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Gap(8)

        Text(
            text = "과목추가",
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelDisable_282119_12
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddSubjectCardPreview() {
    AddSubjectCard(
        Modifier,
        {}
    )
}


