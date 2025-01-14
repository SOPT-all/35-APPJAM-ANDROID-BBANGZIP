package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.card.FriendCardModel
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun FriendCard(
    state: BbangZipCardState,
    data: FriendCardModel,
    modifier: Modifier = Modifier,
) {
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
                )
                // TODO 밤에 코리와 함께 고칠게요 이거 나중가면 이미지가 48.dp로 내려오나?

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

@Preview(showBackground = true, name = "Friend Card Preview")
@Composable
fun FriendCardPreview() {
    BBANGZIPTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            FriendCard(
                state = BbangZipCardState.DEFAULT,
                data =
                    FriendCardModel(
                        friendId = 1,
                        friendName = "김철수",
                        imageUrl = "https://via.placeholder.com/150",
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )
            FriendCard(
                state = BbangZipCardState.CHECKABLE,
                data =
                    FriendCardModel(
                        friendId = 2,
                        friendName = "이영희",
                        imageUrl = "https://via.placeholder.com/150",
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )
            FriendCard(
                state = BbangZipCardState.CHECKED,
                data =
                    FriendCardModel(
                        friendId = 3,
                        friendName = "박영수",
                        imageUrl = "https://via.placeholder.com/150",
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
    }
}
