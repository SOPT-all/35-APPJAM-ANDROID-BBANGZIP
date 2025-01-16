package org.android.bbangzip.presentation.component.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipMenuTopBar(
    title: String = "",
    onMenuClick: () -> Unit = {},
    onBackArrowClick: () -> Unit = {}
){
    BbangZipTopBarSlot(
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevronleft_thick_small_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                        onClick = { onBackArrowClick() }
                    )
            )
        },
        label = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
                style = BbangZipTheme.typography.headline1Bold
            )
        },
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_kebab_default_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                        onClick = { onMenuClick() }
                    )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BbangZipMenuTopBarPreview(){
    BbangZipMenuTopBar(
        title = "경제통계학"
    )
}