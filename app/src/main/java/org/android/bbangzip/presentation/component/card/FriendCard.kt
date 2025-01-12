package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.card.FriendCardModel
import org.android.bbangzip.presentation.type.BbangZipCardStateType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun FriendCard(
    stateType: BbangZipCardStateType,
    data: FriendCardModel,
    modifier: Modifier = Modifier,
) {
    val style = stateType.getStyle()

    Box(
        modifier =
            modifier
                .applyShadows(shadowOptions = style.shadowOptions, shape = RoundedCornerShape(style.radius))
                .fillMaxWidth()
                .border(width = style.borderWidth, color = style.borderColor, shape = RoundedCornerShape(size = style.radius))
                .background(color = style.background, shape = RoundedCornerShape(size = style.radius))
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
                    contentDescription = "프로필 이미지입니다.",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(48.dp),
                ) // TODO 밤에 코리와 함께 고칠게요 이거 나중가면 이미지가 48.dp로 내려오나?

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = data.friendName,
                    style = BbangZipTheme.typography.headline1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "사장님",
                    style = BbangZipTheme.typography.caption2Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61,
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_kebab_default_24),
                contentDescription = "더보기 버튼입니다.",
                tint = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        }
    }
}
