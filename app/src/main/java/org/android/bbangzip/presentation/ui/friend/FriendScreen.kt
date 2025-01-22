package org.android.bbangzip.presentation.ui.friend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.card.FriendCard
import org.android.bbangzip.presentation.component.textfield.BbangZipBasicTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.type.FriendMenuType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendScreen(
    friendState: FriendContract.FriendState,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberLazyListState()
    val isShadowed by remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 0
        }
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp)
            .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState
    ) {
        stickyHeader {
            BbangZipBaseTopBar(
                leadingIcon = R.drawable.ic_user_plus_default_24,
                trailingIcon = R.drawable.ic_bell_default_24,
                backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                isShadowed = isShadowed
            )
        }
        item {
            FriendHeader(selectedMenuItem = friendState.menuFilterType)

            Spacer(modifier = Modifier.height(48.dp))
        }

        item {
            BbangZipBasicTextField(
                leadingIcon = R.drawable.ic_bubble_default_24,
                placeholder = R.string.friend_all_tf_placeholder,
                guideline = R.string.emtpy_string,
                value = "",
                maxCharacter = 20,
                onValueChange = {},
                modifier = Modifier.padding(horizontal = 16.dp),
                focusManager = LocalFocusManager.current,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(R.string.friend_boss_count, friendState.friendList.size),
                    style = BbangZipTheme.typography.label1Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = friendState.selectedFilter,
                        style = BbangZipTheme.typography.label1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_chevrondown_thick_small_24), contentDescription = null, tint = BbangZipTheme.colors.labelAlternative_282119_61,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        items(count = friendState.friendList.size,
            key = { friendState.friendList[it].friendId }) { index ->
            FriendCard(
                data = friendState.friendList[index],
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
fun FriendHeader(
    selectedMenuItem: FriendMenuType, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                        shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
                    ),
            ) {
                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(R.string.friend_header_title),
                    style = BbangZipTheme.typography.title3Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                    modifier = Modifier.padding(start = 24.dp)
                )

                Spacer(modifier = Modifier.height(72.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        FriendMenuTab(
            selectedMenuItem = selectedMenuItem, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun FriendMenuTab(
    selectedMenuItem: FriendMenuType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier =
        modifier
            .applyShadows(BbangZipShadowType.EMPHASIZE, shape = RoundedCornerShape(size = 32.dp))
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(size = 32.dp),
            )
            .background(
                color = BbangZipTheme.colors.staticWhite_FFFFFF,
                shape = RoundedCornerShape(size = 32.dp),
            )
            .padding(horizontal = 48.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            FriendMenuType.entries.forEach { menu ->
                FriendMenuCard(
                    menu = menu,
                    isSelected = selectedMenuItem == menu,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun FriendMenuCard(
    menu: FriendMenuType,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val menuTextColor = if (isSelected) BbangZipTheme.colors.labelNormal_282119
    else BbangZipTheme.colors.labelAssistive_282119_28
    val indicatorBackgroundColor = if (isSelected) BbangZipTheme.colors.labelNormal_282119
    else BbangZipTheme.colors.staticWhite_FFFFFF
    var itemWidth by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(id = menu.id),
            style = BbangZipTheme.typography.body1Bold,
            color = menuTextColor,
            modifier = Modifier
                .wrapContentSize()
                .onSizeChanged { layout ->
                    itemWidth = layout.width
                }
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .height(2.dp)
                .width((itemWidth / 3).dp)
                .background(color = indicatorBackgroundColor, shape = RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendScreenPreview() {
    BBANGZIPTheme {
    }
}
