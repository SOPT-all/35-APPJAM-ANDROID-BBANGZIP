package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.component.pushicon.BbangZipPushIconWithText
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SubjectCard(
    data: SubjectCardModel,
    modifier: Modifier = Modifier,
    onClick: (Int, String) -> Unit = { _, _ -> },
) {
    val radius = data.state.getRadius()
    val width = (LocalConfiguration.current.screenWidthDp - 48) / 2
    Box(
        modifier =
        modifier
            .applyShadows(
                shadowType = data.state.getShadowOptions(),
                shape = RoundedCornerShape(radius),
            )
            .border(
                width = data.state.getBorderWidth(),
                color = data.state.getBorderColor(),
                shape = RoundedCornerShape(size = radius),
            )
            .width(width = width.dp)
            .aspectRatio(156 / 190f)
            .background(
                color = data.state.getBackgroundColor(),
                shape = RoundedCornerShape(size = radius),
            )
            .applyFilterOnClick(
                baseColor = data.state.getBackgroundColor(),
                isDisabled = false,
                radius = radius,
            ) { onClick(data.subjectId, data.subjectName) }
            .padding(end = 8.dp, start = 16.dp, top = 16.dp, bottom = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            SubjectInfo(
                data = data,
                modifier = Modifier.weight(1f),
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevronright_thick_small_24),
                tint = BbangZipTheme.colors.labelAssistive_282119_28,
                contentDescription = stringResource(R.string.card_subject_button_description),
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (data.examName.isEmpty() || data.examRemainingDays == -999) {
            Text(
                text = "공부를 추가해주세요",
                style = BbangZipTheme.typography.label2Bold,
                color = BbangZipTheme.colors.labelNeutral_282119_88,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        } else {
            Text(
                text = data.examName,
                style = BbangZipTheme.typography.label2Bold,
                color = BbangZipTheme.colors.labelNeutral_282119_88,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Gap(height = 4)

            BbangZipChip(
                text = (stringResource(R.string.card_d_minus_day_text, data.pendingCount)),
                backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
            )

            Gap()

            Column {
                BbangZipPushIconWithText(
                    backgroundColor = BbangZipTheme.colors.statusDestructive_FF8345,
                    count = data.pendingCount,
                    text = stringResource(R.string.card_subject_pending_icon_description),
                )

                Spacer(modifier = Modifier.height(4.dp))

                BbangZipPushIconWithText(
                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                    count = data.inProgressCount,
                    text = stringResource(R.string.card_subject_studying_icon_description),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectCardPreview() {
    BBANGZIPTheme {
        Column {
            SubjectCard(
                data =
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1,
                    state = BbangZipCardState.DEFAULT,
                ),
                modifier = Modifier.padding(16.dp),
            )
            SubjectCard(
                data =
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1,
                    state = BbangZipCardState.CHECKED,
                ),
                modifier = Modifier.padding(16.dp),
            )
            SubjectCard(
                data =
                SubjectCardModel(
                    subjectName = "[경영] 해ㅜ저해줘재훠재헞",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1,
                    state = BbangZipCardState.CHECKABLE,
                ),
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
