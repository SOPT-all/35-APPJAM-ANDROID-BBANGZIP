package org.android.bbangzip.presentation.ui.my.mybadgecategory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipBasicModalBottomSheet
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.model.BadgeDetail
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipBadgeDetailBottomSheet(
    badgeDetail: BadgeDetail,
    isBottomSheetVisible: Boolean,
    cancelButtonText: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onClickCancelButton: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (!badgeDetail.isLocked) {
                    BadgeDetailInfo(badgeDetail = badgeDetail)
                } else {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(48.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .blur(48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.height(44.dp))

                            AsyncImage(
                                model = badgeDetail.imageUrl,
                                contentDescription = null,
                                modifier =
                                    Modifier
                                        .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(48.dp)),
                            )

                            Spacer(modifier = Modifier.height(160.dp))
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock_default_28),
                            contentDescription = null,
                            tint = BbangZipTheme.colors.lineStrong_68645E_52,
                            modifier =
                                Modifier
                                    .height(58.dp)
                                    .width(41.dp),
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                }
                Column(
                    modifier =
                        Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(R.string.badge_detail_achievement),
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.primaryNormal_282119,
                    )

                    Text(
                        text = badgeDetail.achievementCondition,
                        style = BbangZipTheme.typography.label1Bold,
                        color = BbangZipTheme.colors.labelAlternative_282119_61,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier =
                        Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.badge_detail_reward),
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.primaryNormal_282119,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    BbangZipChip(
                        backgroundColor = BbangZipTheme.colors.statusCautionary_FFB84A,
                        text =
                            stringResource(
                                R.string.badge_bottom_sheet_reward,
                                badgeDetail.reward,
                            ),
                    )
                }
            }
        },
        cancelButton = {
            Spacer(modifier = Modifier.height(32.dp))

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onClickCancelButton() },
                label = cancelButtonText,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = R.drawable.ic_chevronright_thick_small_24,
            )

            Spacer(modifier = Modifier.height(16.dp))
        },
    )
}

@Composable
fun HashTagText(
    hashTags: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "#${hashTags[0]}",
            style = BbangZipTheme.typography.body2Bold,
            color = BbangZipTheme.colors.labelAssistive_282119_28,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "#${hashTags[1]}",
                style = BbangZipTheme.typography.body2Bold,
                color = BbangZipTheme.colors.labelAssistive_282119_28,
            )

            if (hashTags.size > 2) {
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "#${hashTags[2]}",
                    style = BbangZipTheme.typography.body2Bold,
                    color = BbangZipTheme.colors.labelAssistive_282119_28,
                )
            }
        }
    }
}

@Composable
fun BadgeDetailInfo(
    badgeDetail: BadgeDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(44.dp))

        AsyncImage(
            model = badgeDetail.imageUrl,
            contentDescription = null,
            modifier =
                Modifier
                    .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(48.dp)),
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    BottomSheetBalloonContainer(text = badgeDetail.categoryName)

    Spacer(modifier = Modifier.height(32.dp))

    HashTagText(badgeDetail.hashTags)

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun BottomSheetBalloonContainer(
    text: String,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .applyShadows(BbangZipShadowType.STRONG, shape = RoundedCornerShape(size = 20.dp))
                .background(
                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                    shape = RoundedCornerShape(size = 20.dp),
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon()

            Text(
                text = text,
                style = BbangZipTheme.typography.body1Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
            )

            trailingIcon()
        }
    }
}
