package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.card.model.BbangZipCardData
import org.android.bbangzip.presentation.type.BbangZipCardStateType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme


@Composable
fun SubjectCard(
    stateType: BbangZipCardStateType,
    data: BbangZipCardData.SubjectCardData,
    modifier: Modifier = Modifier
) {
    val style = stateType.getStyle()

    Box(
        modifier = modifier
            .applyShadows(shadowOptions = style.shadowOptions, shape = RoundedCornerShape(style.radius))
            .height(190.dp) // TODO 기기대응 어케할지 물어보기
            .border(width = style.borderWidth, color = style.borderColor, shape = RoundedCornerShape(size = style.radius))
            .background(color = style.background, shape = RoundedCornerShape(size = style.radius))
            .padding(end = 8.dp, start = 16.dp, top = 16.dp, bottom = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SubjectInfo(
                data = data
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevronright_thick_small_24),
                tint = BbangZipTheme.colors.labelAssistive_282119_28,
                contentDescription = "오늘 할 일로 향하는 버튼입니다."
            )
        }
    }
}

@Composable
fun SubjectInfo(
    data: BbangZipCardData.SubjectCardData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
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

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = BbangZipTheme.colors.statusPositive_3D3730, shape = RoundedCornerShape(11.dp))
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "D-24",
                    style = BbangZipTheme.typography.caption1Medium,
                    color = BbangZipTheme.colors.staticWhite_FFFFFF
                )
            }//TODO 칩구현
        }
        Column {
            StudyStateCountCircle(
                backgroundColor = BbangZipTheme.colors.statusDestructive_FF8345,
                count = data.pendingCount,
                text = "밀린 공부"
            )
            Spacer(modifier = Modifier.height(4.dp))

            StudyStateCountCircle(
                backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                count = data.inProgressCount,
                text = "진행 중인 공부"
            )
        }

    }
}

@Composable
fun StudyStateCountCircle(
    backgroundColor: Color,
    count: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(color = backgroundColor, shape = CircleShape)
                .padding(horizontal = 6.dp, vertical = 3.dp)
        ) {
            Text(
                text = count.toString(),
                style = BbangZipTheme.typography.caption2Bold,
                color = BbangZipTheme.colors.staticWhite_FFFFFF
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = BbangZipTheme.typography.caption1Bold,
            color = BbangZipTheme.colors.labelAssistive_282119_28
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectCardPreview() {
    BBANGZIPTheme {
        Column {
            SubjectCard(
                stateType = BbangZipCardStateType.DEFAULT, // 필요한 상태 타입 설정
                data = BbangZipCardData.SubjectCardData(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0, // 밀린 공부 개수
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1 // 진행 중인 공부 개수
                ),
                modifier = Modifier
                    .padding(16.dp) // 카드가 화면에 잘 보이도록 여백 추가
            )
            SubjectCard(
                stateType = BbangZipCardStateType.CHECKED, // 필요한 상태 타입 설정
                data = BbangZipCardData.SubjectCardData(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0, // 밀린 공부 개수
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1 // 진행 중인 공부 개수
                ),
                modifier = Modifier
                    .padding(16.dp) // 카드가 화면에 잘 보이도록 여백 추가
            )
            SubjectCard(
                stateType = BbangZipCardStateType.CHECKABLE, // 필요한 상태 타입 설정
                data = BbangZipCardData.SubjectCardData(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0, // 밀린 공부 개수
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1 // 진행 중인 공부 개수
                ),
                modifier = Modifier
                    .padding(16.dp) // 카드가 화면에 잘 보이도록 여백 추가
            )
        }
    }
}