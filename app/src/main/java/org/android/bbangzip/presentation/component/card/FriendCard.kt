package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.card.FriendCardModel
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun FriendCard(
    data: FriendCardModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {},
) {
    val state = data.state
    val radius = state.getRadius()
    Box(
        modifier =
            modifier
                .applyShadows(
                    shadowType = state.getShadowOptions(),
                    shape = RoundedCornerShape(radius),
                )
                .fillMaxWidth()
                .border(
                    width = state.getBorderWidth(),
                    color = state.getBorderColor(),
                    shape = RoundedCornerShape(size = radius),
                )
                .background(
                    color = state.getBackgroundColor(),
                    shape = RoundedCornerShape(size = radius),
                )
                .applyFilterOnClick(baseColor = state.getBackgroundColor(), radius = radius) { onClick() }
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_user_one_default_24),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(48.dp),
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = data.friendName,
                    style = BbangZipTheme.typography.headline1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.card_friend_boss_text),
                    style = BbangZipTheme.typography.caption2Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61,
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_kebab_default_24),
                contentDescription = stringResource(R.string.card_friend_kebab_button_description),
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
                modifier = Modifier.clickable { onActionIconClick() },
            )
        }
    }
}
