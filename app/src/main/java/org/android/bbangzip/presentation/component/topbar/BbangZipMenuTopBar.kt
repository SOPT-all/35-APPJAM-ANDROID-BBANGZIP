package org.android.bbangzip.presentation.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onMenuClick: () -> Unit = {},
    onBackArrowClick: () -> Unit = {}
){
    BbangZipTopBarSlot(
        leadingIcon = {
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ){
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .applyFilterOnClick(
                                radius = 20.dp,
                                isDisabled = false,
                                onClick = { onBackArrowClick() }
                            )
                    )
                }
            }
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
           Box(
               modifier = Modifier
                   .width(56.dp)
                   .height(56.dp),
               contentAlignment = Alignment.Center
           ) {
                trailingIcon?.let {
                    Icon(
                        imageVector = it,
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
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BbangZipMenuTopBarPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BbangZipMenuTopBar(
            title = "경제통계학",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_chevronleft_thick_small_24),
        )

        BbangZipMenuTopBar(
            title = "",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_chevronleft_thick_small_24),
        )

        BbangZipMenuTopBar(
            title = "",
        )

        BbangZipMenuTopBar(
            title = "경제통계학",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_chevronleft_thick_small_24),
            trailingIcon = ImageVector.vectorResource(id = R.drawable.ic_menu_kebab_default_24),

            )
    }
}