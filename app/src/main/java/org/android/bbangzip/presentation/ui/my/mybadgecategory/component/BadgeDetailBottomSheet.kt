package org.android.bbangzip.presentation.ui.my.mybadgecategory.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.balloon.TopTailBalloon
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipBasicModalBottomSheet
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.model.BadgeDetail
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(44.dp))

                AsyncImage(
                    model = badgeDetail.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                        .clip(RoundedCornerShape(48.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))

                TopTailBalloon(text = badgeDetail.name, horizontalPadding = 132.dp)

                Spacer(modifier = Modifier.height(32.dp))

                HashTagText(badgeDetail.hashTags)

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth()
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
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(R.string.badge_detail_reward),
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.primaryNormal_282119,
                    )

                    BbangZipChip(
                        backgroundColor = BbangZipTheme.colors.statusCautionary_FFB84A,
                        text = badgeDetail.reward.toString(),
                    )
                }

            }
        },
        cancelButton = {
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onClickCancelButton,
            ) {
                Text(
                    text = cancelButtonText,
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.primaryNormal_282119,
                )
            }
        },
    )
}

@Composable
fun HashTagText(
    hashTags: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "#${hashTags[0]}",
            style = BbangZipTheme.typography.body2Bold,
            color = BbangZipTheme.colors.labelAssistive_282119_28,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
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
