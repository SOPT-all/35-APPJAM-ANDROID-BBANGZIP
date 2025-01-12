package org.android.bbangzip.presentation.ui.navigator.component

import android.content.res.Resources.Theme
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import okhttp3.internal.toImmutableList
import org.android.bbangzip.R
import org.android.bbangzip.presentation.type.BottomNavigationType
import org.android.bbangzip.presentation.util.modifier.dropShadow
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import org.android.bbangzip.ui.theme.defaultBbangZipTypography

@Composable
fun BottomNavigationBar(
    isVisible: Boolean,
    bottomNaviBarItems: List<BottomNavigationType>,
    currentNaviBarItemSelected: BottomNavigationType?,
    onBottomNaviBarItemSelected: (BottomNavigationType) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(),
        exit = slideOutVertically(),
    ) {
        Box(
            modifier =
                modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .dropShadow(
                        offsetY = (-4).dp,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    ),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                        )
                        .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF)
                        .padding(top = 12.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                bottomNaviBarItems.forEach { navItem ->
                    BottomNavigationItem(
                        isSelected = currentNaviBarItemSelected == navItem,
                        bottomNaviType = navItem,
                        onBottomNaviBarItemSelected = onBottomNaviBarItemSelected,
                        bottomNaviIcon = navItem.bottomNaviIcon,
                        bottomNaviTitle = navItem.bottomNaviTitle,
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationItem(
    isSelected: Boolean,
    bottomNaviType: BottomNavigationType,
    onBottomNaviBarItemSelected: (BottomNavigationType) -> Unit,
    @DrawableRes bottomNaviIcon: Int,
    @StringRes bottomNaviTitle: Int,
    modifier: Modifier = Modifier,
    spacing: Dp = 4.dp,
) {
    Column(
        modifier =
            modifier
                .noRippleClickable {
                    onBottomNaviBarItemSelected(bottomNaviType)
                },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 24.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = bottomNaviIcon),
                contentDescription = stringResource(id = R.string.app_name),
                tint =
                    if (isSelected) {
                        BbangZipTheme.colors.labelNormal_282119
                    } else {
                        BbangZipTheme.colors.labelAssistive_282119_28
                    },
            )
        }

        Spacer(modifier = Modifier.height(spacing))

        Text(
            text = stringResource(bottomNaviTitle),
            color =
                if (isSelected) {
                    BbangZipTheme.colors.labelNormal_282119
                } else {
                    BbangZipTheme.colors.labelAssistive_282119_28
                },
            style = BbangZipTheme.typography.caption1Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BBANGZIPTheme {
        BottomNavigationBar(
            isVisible = true,
            bottomNaviBarItems = BottomNavigationType.entries.toImmutableList(),
            currentNaviBarItemSelected = BottomNavigationType.SUBJECT,
            onBottomNaviBarItemSelected = {},
        )
    }
}
