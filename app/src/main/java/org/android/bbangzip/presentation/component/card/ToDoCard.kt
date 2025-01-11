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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.type.StudyCardData
import org.android.bbangzip.presentation.type.StudyCardStateType
import org.android.bbangzip.presentation.type.ToDoCardStateType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun ToDoCard(
    stateType: StudyCardStateType,
    data: StudyCardData.TodoCardData,
    modifier: Modifier = Modifier
) {
    val style = stateType.getStyle()
    val isComplete = stateType == ToDoCardStateType.COMPLETE
    val applicableShadows = if (isComplete) {
        style.shadowOptions.take(1)
    } else {
        style.shadowOptions
    }
    val background = when (stateType) {
        StudyCardStateType.DEFAULT -> BbangZipTheme.colors.fillStrong_68645E_16
        StudyCardStateType.CHECKED -> BbangZipTheme.colors.fillStrong_68645E_16
        StudyCardStateType.CHECKABLE -> BbangZipTheme.colors.fillStrong_68645E_16
        ToDoCardStateType.COMPLETE -> BbangZipTheme.colors.secondaryNormal_FFCD80
    }
    Box(
        modifier = modifier
            .applyShadows(shadowOptions = applicableShadows, shape = RoundedCornerShape(style.radius))
            .fillMaxWidth()
            .border(width = style.borderWidth, color = style.borderColor, shape = RoundedCornerShape(size = style.radius))
            .background(color = style.background, shape = RoundedCornerShape(size = style.radius))
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ToDoInfo(data = data)
            CheckSpace(
                backgroundColor = background,
                checkIcon = if (isComplete) {
                    {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_thick_24),
                            contentDescription = "체크 표시 아이콘입니다.",
                            tint = BbangZipTheme.colors.staticWhite_FFFFFF
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}


@Composable
fun ToDoInfo(
    data: StudyCardData.TodoCardData,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column  (modifier =Modifier.padding(start = 4.dp)){
            Text(
                text = "${data.subjectName} / ${data.examName}",
                style = BbangZipTheme.typography.caption2Medium,
                color = BbangZipTheme.colors.labelAssistive_282119_28
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = data.studyContents,
                style = BbangZipTheme.typography.caption1Medium,
                color = BbangZipTheme.colors.labelAlternative_282119_61
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${data.startPage}p - ${data.finishPage}p",
                style = BbangZipTheme.typography.label1Bold,
                color = BbangZipTheme.colors.labelNormal_282119
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = BbangZipTheme.colors.statusDestructive_FF8345, shape = RoundedCornerShape(11.dp))
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(text = "D+5", style = BbangZipTheme.typography.caption1Medium, color = BbangZipTheme.colors.staticWhite_FFFFFF)
            }// TODO 칩 구현

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${data.deadline}까지",
                style = BbangZipTheme.typography.caption1Bold,
                color = BbangZipTheme.colors.labelAlternative_282119_61
            )
        }
    }
}

@Composable
fun CheckSpace(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    checkIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .height(32.dp)
            .width(32.dp)
        //TODO 여기 어떤식으로 기기대응하는지 물어보기
    ) {
        checkIcon?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoCardPreview() {
    BBANGZIPTheme {
        Column {
            ToDoCard(
                stateType = StudyCardStateType.DEFAULT,
                data = StudyCardData.TodoCardData(
                    subjectName = "경제통계학개론",
                    examName = "중간고사",
                    studyContents = "경제통계학",
                    startPage = 36,
                    finishPage = 60,
                    deadline = "2025년 4월 25일",
                    pieceId = "1",
                    remainingDays = 1
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
            ToDoCard(
                stateType = StudyCardStateType.CHECKED,
                data = StudyCardData.TodoCardData(
                    subjectName = "경제통계학개론",
                    examName = "중간고사",
                    studyContents = "경제통계학",
                    startPage = 36,
                    finishPage = 60,
                    deadline = "2025년 4월 25일",
                    pieceId = "1",
                    remainingDays = 1
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
            ToDoCard(
                stateType = StudyCardStateType.CHECKABLE,
                data = StudyCardData.TodoCardData(
                    subjectName = "경제통계학개론",
                    examName = "중간고사",
                    studyContents = "경제통계학",
                    startPage = 36,
                    finishPage = 60,
                    deadline = "2025년 4월 25일",
                    pieceId = "1",
                    remainingDays = 1
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
            ToDoCard(
                stateType = ToDoCardStateType.COMPLETE,
                data = StudyCardData.TodoCardData(
                    subjectName = "경제통계학개론",
                    examName = "중간고사",
                    studyContents = "경제통계학",
                    startPage = 36,
                    finishPage = 60,
                    deadline = "2025년 4월 25일",
                    pieceId = "1",
                    remainingDays = 1
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

