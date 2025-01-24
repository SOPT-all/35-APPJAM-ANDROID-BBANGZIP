package org.android.bbangzip.presentation.ui.my

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipTwoButtonBottomSheet
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.ui.my.component.BbangZipLevelProgressBar
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun MyScreen(
    padding: PaddingValues,
    state: MyContract.MyState,
    onClickBbangZip: () -> Unit,
    onClickBadgeCount: () -> Unit,
    onClickLogoutBtn: () -> Unit,
    onClickWithdrawBtn: () -> Unit,
    onClickLogoutConfirmBtn: () -> Unit,
    onClickWithdrawConfirmBtn: () -> Unit,
    onClickLogoutCancelBtn: () -> Unit,
    onClickWithdrawCancelBtn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    (LocalView.current.context as Activity).window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    LazyColumn(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        item {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier =
                    Modifier
                        .height(height = (LocalConfiguration.current.screenHeightDp * 0.635).dp),
            ) {
                MainBbangZip(
                    modifier =
                        Modifier
                            .noRippleClickable { onClickBbangZip() },
                    level = state.myBbangZip?.bbangZipLevel ?: 1,
                    currentPoint = state.myBbangZip?.reward ?: 0,
                    maxPoint = state.myBbangZip?.maxReward ?: 0,
                    bbangZipImgUrl = state.myBbangZip?.bbangZipImgUrl ?: "https://cdn-icons-png.flaticon.com/512/3348/3348027.png",
                    bbangZipName = state.myBbangZip?.bbangZipName ?: "",
                )

                MyBadgeInfo(
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 16.dp),
                    currentBadge = state.currentBadge.toString(),
                    onClickBadgeCount = onClickBadgeCount,
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(88.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_profile_setting),
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyHorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_notice),
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_personal_terms),
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_service_terms),
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyHorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_logout),
                modifier = Modifier.padding(horizontal = 16.dp),
                onClickMenu = { onClickLogoutBtn() },
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            MyMenuTitle(
                text = stringResource(R.string.my_withdraw),
                modifier = Modifier.padding(horizontal = 16.dp),
                onClickMenu = { onClickWithdrawBtn() },
            )
        }

        item {
            Spacer(modifier = Modifier.height(padding.calculateBottomPadding()))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    BbangZipTwoButtonBottomSheet(
        isBottomSheetVisible = state.logoutBottomSheetState,
        onDismissRequest = { onClickLogoutBtn() },
        bottomSheetTitle = stringResource(R.string.logout_title),
        interactButtonText = stringResource(R.string.logout_confirm),
        cancelButtonText = stringResource(R.string.cancel),
        onClickInteractButton = { onClickLogoutConfirmBtn() },
        onClickCancelButton = { onClickLogoutCancelBtn() },
    )

    BbangZipTwoButtonBottomSheet(
        isBottomSheetVisible = state.withdrawBottomSheetState,
        onDismissRequest = { onClickWithdrawBtn() },
        bottomSheetTitle = stringResource(R.string.withdraw_title),
        interactButtonText = stringResource(R.string.withdraw_confirm),
        cancelButtonText = stringResource(R.string.cancel),
        onClickInteractButton = { onClickWithdrawConfirmBtn() },
        onClickCancelButton = { onClickWithdrawCancelBtn() },
    )
}

@Composable
private fun MainBbangZip(
    bbangZipName: String,
    bbangZipImgUrl: String,
    level: Int,
    currentPoint: Int,
    maxPoint: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
                .height(height = (LocalConfiguration.current.screenHeightDp * 0.482).dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(context = LocalContext.current)
                        .data(data = bbangZipImgUrl)
                        .crossfade(enable = true)
                        .build(),
                contentDescription = null,
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 50.dp),
            )

            BbangZipLevelProgressBar(
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 40.dp)
                        .padding(bottom = 50.dp),
                level = level - 1,
                currentPoint = currentPoint,
                maxPoint = maxPoint,
                bbangZipName = bbangZipName,
            )
        }
    }
}

@Composable
private fun MyBadgeInfo(
    currentBadge: String,
    modifier: Modifier = Modifier,
    onClickBadgeCount: () -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .applyShadows(shape = RoundedCornerShape(40.dp), shadowType = BbangZipShadowType.STRONG)
                .clip(shape = RoundedCornerShape(40.dp))
                .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .border(width = 2.dp, shape = RoundedCornerShape(24.dp), color = BbangZipTheme.colors.lineNormal_68645E_22)
                        .padding(24.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus_default_24),
                    contentDescription = null,
                    modifier = Modifier.size(33.dp),
                    tint = BbangZipTheme.colors.lineNormal_68645E_22,
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.my_badge_setting),
                style = BbangZipTheme.typography.caption2Medium,
                color = BbangZipTheme.colors.labelAssistive_282119_28,
            )
        }

        Column(
            modifier = Modifier.noRippleClickable { onClickBadgeCount() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(21.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = currentBadge,
                    style = BbangZipTheme.typography.title2Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.width(1.dp))

                Text(
                    text = stringResource(R.string.my_badge_book_count),
                    style = BbangZipTheme.typography.body1Medium,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.width(2.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_chevronright_tight_thick_small_24),
                    contentDescription = null,
                    tint = BbangZipTheme.colors.labelAlternative_282119_61,
                )
            }

            Spacer(modifier = Modifier.height(27.dp))

            Text(
                text = stringResource(R.string.my_badge_book),
                style = BbangZipTheme.typography.caption2Medium,
                color = BbangZipTheme.colors.labelAssistive_282119_28,
            )
        }
    }
}

@Composable
private fun MyMenuTitle(
    text: String,
    modifier: Modifier = Modifier,
    onClickMenu: () -> Unit = {},
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .applyFilterOnClick(radius = 24.dp, baseColor = BbangZipTheme.colors.staticWhite_FFFFFF) { onClickMenu() }
                .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chevronright_thick_small_24),
            contentDescription = null,
            tint = BbangZipTheme.colors.labelNormal_282119,
        )
    }
}

@Composable
private fun MyHorizontalDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        thickness = 1.dp,
        color = BbangZipTheme.colors.lineNormal_68645E_22,
    )
}
