package org.android.bbangzip.presentation.component.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipBaseTopBar(
    modifier: Modifier = Modifier,
//    isShadowed: Boolean = false,
    scrollState: LazyListState = rememberLazyListState(),
    backGroundColor: Color = BbangZipTheme.colors.staticWhite_FFFFFF,
    title: String = "",
    titleColor: Color = BbangZipTheme.colors.labelNormal_282119,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {},
    onLeadingIconClick: () -> Unit = {},
) {
    val isShadowed by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset > 0 } }
    Box(
        modifier =
            modifier
                .applyShadows(
                    shadowType = if (isShadowed) BbangZipShadowType.EMPHASIZE else BbangZipShadowType.EMPTY,
                    shape = RectangleShape,
                )
                .background(backGroundColor),
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier =
                    modifier
                        .width(56.dp)
                        .height(56.dp),
                contentAlignment = Alignment.Center,
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = ImageVector.vectorResource(it),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .applyFilterOnClick(
                                    radius = 20.dp,
                                    isDisabled = false,
                                    onClick = { onLeadingIconClick() },
                                )
                                .padding(8.dp),
                        tint = BbangZipTheme.colors.labelAlternative_282119_61,
                    )
                }
            }

            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = titleColor,
                textAlign = TextAlign.Center,
                style = BbangZipTheme.typography.headline1Bold,
            )

            Box(
                modifier =
                    Modifier
                        .width(56.dp)
                        .height(56.dp),
                contentAlignment = Alignment.Center,
            ) {
                trailingIcon?.let {
                    Icon(
                        imageVector = ImageVector.vectorResource(it),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .applyFilterOnClick(
                                    radius = 20.dp,
                                    isDisabled = false,
                                    onClick = { onTrailingIconClick() },
                                )
                                .padding(8.dp),
                        tint = BbangZipTheme.colors.labelAlternative_282119_61,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BbangZipMenuTopBarPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BbangZipBaseTopBar(
            title = "경제통계학",
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
        )

        BbangZipBaseTopBar(
            title = "",
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
        )

        BbangZipBaseTopBar(
            title = "",
        )

        BbangZipBaseTopBar(
            title = "경제통계학",
            backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            trailingIcon = R.drawable.ic_menu_kebab_default_24,
        )
    }
}
