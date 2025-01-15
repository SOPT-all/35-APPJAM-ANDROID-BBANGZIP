package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SubjectCard(
    state: BbangZipCardState,
    data: SubjectCardModel,
    onClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {},
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
            .border(
                width = state.getBorderWidth(),
                color = state.getBorderColor(),
                shape = RoundedCornerShape(size = radius),
            )
            .background(
                color = state.getBackgroundColor(),
                shape = RoundedCornerShape(size = radius),
            )
            .padding(end = 8.dp, start = 16.dp, top = 16.dp, bottom = 16.dp)
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SubjectInfo(
                data = data,
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevronright_thick_small_24),
                tint = BbangZipTheme.colors.labelAssistive_282119_28,
                contentDescription = stringResource(R.string.card_subject_button_description),
                modifier = Modifier
                    .clickable { onActionIconClick() },
            )
        }
    }
}
//TODO
@Composable
fun SubjectInfo(
    data: SubjectCardModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = data.subjectName,
            style = BbangZipTheme.typography.body1Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = data.examName,
            style = BbangZipTheme.typography.label2Bold,
            color = BbangZipTheme.colors.labelNeutral_282119_88,
        )

        Spacer(modifier = Modifier.height(4.dp))

        BbangZipChip(
            text = (stringResource(R.string.card_d_minus_day,data.pendingCount)),
            backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
        )

        Spacer(modifier = Modifier.height(44.dp))

        Column {
            BbangZipPushIcon(
                backgroundColor = BbangZipTheme.colors.statusDestructive_FF8345,
                count = data.pendingCount,
                text = stringResource(R.string.card_subject_pending_icon_description),
            )

            Spacer(modifier = Modifier.height(4.dp))

            BbangZipPushIcon(
                backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                count = data.inProgressCount,
                text = stringResource(R.string.card_subject_studying_icon_description),
            )
        }
    }
}

@Composable
fun BbangZipPushIcon(
    backgroundColor: Color,
    count: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                modifier
                    .background(color = backgroundColor, shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 3.dp),
        ) {
            Text(
                text = count.toString(),
                style = BbangZipTheme.typography.caption2Bold,
                color = BbangZipTheme.colors.staticWhite_FFFFFF,
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = BbangZipTheme.typography.caption1Bold,
            color = BbangZipTheme.colors.labelAssistive_282119_28,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectCardPreview() {
    BBANGZIPTheme {
        Column {
            SubjectCard(
                state = BbangZipCardState.DEFAULT,
                data =
                    SubjectCardModel(
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        pendingCount = 0,
                        inProgressCount = 6,
                        subjectId = 1,
                        examRemainingDays = 1,
                    ),
                modifier = Modifier.padding(16.dp),
            )
            SubjectCard(
                state = BbangZipCardState.CHECKED,
                data =
                    SubjectCardModel(
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        pendingCount = 0,
                        inProgressCount = 6,
                        subjectId = 1,
                        examRemainingDays = 1,
                    ),
                modifier = Modifier.padding(16.dp),
            )
            SubjectCard(
                state = BbangZipCardState.CHECKABLE,
                data =
                    SubjectCardModel(
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        pendingCount = 0,
                        inProgressCount = 6,
                        subjectId = 1,
                        examRemainingDays = 1,
                    ),
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
