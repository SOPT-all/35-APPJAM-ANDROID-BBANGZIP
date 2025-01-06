package org.android.bbangzip.presentation.ui.navigator.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import okhttp3.internal.toImmutableList
import org.android.bbangzip.R
import org.android.bbangzip.presentation.type.BottomNavigationType
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@Composable
fun BottomNavigationBar(
    isVisible: Boolean,
    bottomNaviBarItems: List<BottomNavigationType>,
    currentNaviBarItemSelected: BottomNavigationType?,
    onBottomNaviBarItemSelected: (BottomNavigationType) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(visible = isVisible) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
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
            modifier.noRippleClickable {
                onBottomNaviBarItemSelected(bottomNaviType)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = bottomNaviIcon),
            contentDescription = stringResource(id = R.string.app_name),
            tint =
                if (isSelected) {
                    Color.Gray
                } else {
                    Color.White
                },
        )

        Spacer(modifier = Modifier.height(spacing))

        Text(
            text = stringResource(bottomNaviTitle),
            color =
                if (isSelected) {
                    Color.Gray
                } else {
                    Color.White
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BBANGZIPTheme {
        BottomNavigationBar(
            isVisible = true,
            modifier =
                Modifier
                    .background(Color.Blue)
                    .padding(top = 5.dp, bottom = 10.dp),
            bottomNaviBarItems = BottomNavigationType.entries.toImmutableList(),
            currentNaviBarItemSelected = BottomNavigationType.DUMMY,
            onBottomNaviBarItemSelected = {},
        )
    }
}
