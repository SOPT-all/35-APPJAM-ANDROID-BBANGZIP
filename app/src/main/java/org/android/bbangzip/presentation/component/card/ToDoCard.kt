package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun ToDoCard(
    state: BbangZipCardState,
    data: ToDoCardModel,
    modifier: Modifier = Modifier,
    isDeleted: Boolean = false,
    onClick: () -> Unit = {},
) {
    val radius = state.getRadius()
    val infoOpacity = state.getInfoOpacity()
    Box(
        modifier =
            modifier
                .applyShadows(
                    shadowType = state.getShadowOptions(),
                    shape = RoundedCornerShape(size = radius),
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
                .applyFilterOnClick(
                    baseColor = state.getBackgroundColor(),
                    isDisabled = isDeleted,
                    radius = radius,
                ) { if (!isDeleted) onClick() }
                .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ToDoInfo(
                data = data,
                infoOpacity = infoOpacity,
                isComplete = state == BbangZipCardState.COMPLETE,
            )

            CheckSpace(
                backgroundColor = state.getCheckBoxBackgroundColor(),
                isCompleted = state == BbangZipCardState.COMPLETE,
            )
        }
    }
}

@Composable
fun ToDoInfo(
    data: ToDoCardModel,
    infoOpacity: Float,
    isComplete: Boolean,
    modifier: Modifier = Modifier,
) {
    fun getColor(baseColor: Color): Color {
        return if (isComplete) baseColor.copy(alpha = infoOpacity) else baseColor
    }

    val count =
        if (data.remainingDays >= 0) {
            stringResource(R.string.card_d_minus)
        } else {
            stringResource(R.string.card_d_plus)
        }

    val chipBackgroundColor =
        if (data.remainingDays >= 0) {
            getColor(BbangZipTheme.colors.labelAlternative_282119_61)
        } else {
            getColor(BbangZipTheme.colors.statusDestructive_FF8345)
        }

    Column(modifier = modifier) {
        Column(modifier = Modifier.padding(start = 4.dp)) {
            if (data.subjectName.isNotBlank() && data.examName.isNotBlank()) {
                Text(
                    text =
                        stringResource(
                            R.string.card_subject_exam_name_text,
                            data.subjectName,
                            data.examName,
                        ),
                    style = BbangZipTheme.typography.caption2Medium,
                    color = getColor(BbangZipTheme.colors.labelAssistive_282119_28),
                )

                Spacer(modifier = Modifier.height(2.dp))
            }

            Text(
                text = data.studyContents,
                style = BbangZipTheme.typography.caption1Medium,
                color = getColor(BbangZipTheme.colors.labelAlternative_282119_61),
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text =
                    stringResource(
                        R.string.card_start_end_day_text,
                        data.startPage,
                        data.finishPage,
                    ),
                style = BbangZipTheme.typography.label1Bold,
                color = getColor(BbangZipTheme.colors.labelNormal_282119),
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            BbangZipChip(
                text = count + data.remainingDays,
                backgroundColor = chipBackgroundColor,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.card_deadline_text, data.deadline),
                style = BbangZipTheme.typography.caption1Bold,
                color = getColor(BbangZipTheme.colors.labelAlternative_282119_61),
            )
        }
    }
}

@Composable
fun CheckSpace(
    backgroundColor: Color,
    isCompleted: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .height(32.dp)
                .width(32.dp),
    ) {
        if (isCompleted) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_thick_24),
                contentDescription = stringResource(R.string.card_todo_check_space_description),
                tint = BbangZipTheme.colors.staticWhite_FFFFFF,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoCardPreview() {
    BBANGZIPTheme {
        Column {
            ToDoCard(
                state = BbangZipCardState.DEFAULT,
                data =
                    ToDoCardModel(
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        pieceId = "1",
                        remainingDays = 1,
                    ),
                modifier =
                    Modifier
                        .padding(16.dp),
            )
            ToDoCard(
                state = BbangZipCardState.CHECKABLE,
                data =
                    ToDoCardModel(
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        pieceId = "1",
                        remainingDays = 1,
                    ),
                modifier =
                    Modifier
                        .padding(16.dp),
            )
            ToDoCard(
                state = BbangZipCardState.CHECKED,
                data =
                    ToDoCardModel(
                        subjectName = "",
                        examName = "",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        pieceId = "1",
                        remainingDays = 1,
                    ),
                modifier =
                    Modifier
                        .padding(16.dp),
            )
            ToDoCard(
                state = BbangZipCardState.COMPLETE,
                data =
                    ToDoCardModel(
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        pieceId = "1",
                        remainingDays = 1,
                    ),
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }
}
