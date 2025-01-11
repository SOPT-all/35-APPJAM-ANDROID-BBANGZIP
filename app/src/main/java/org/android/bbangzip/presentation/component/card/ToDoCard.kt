package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.type.StudyCardData
import org.android.bbangzip.presentation.type.StudyCardStyle
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun ToDoCard(
    style: StudyCardStyle,
    data: StudyCardData.TodoCardData,
    isComplete: Boolean,
    modifier: Modifier = Modifier
) {
    val applicableShadows = if (isComplete) {
        style.shadowOptions.take(1)
    } else {
        style.shadowOptions
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(width = style.borderWidth, color = style.borderColor, shape = RoundedCornerShape(size = style.radius))
            .background(color = style.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .applyShadows(shadowOptions = applicableShadows, shape = RoundedCornerShape(style.radius))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ToDoInfo(data = data)
            Icon()
        }
    }
}

@Composable
fun ToDoInfo(
    data: StudyCardData.TodoCardData,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "${data.subjectName} / ${data.examName}",
            style = BbangZipTheme.typography.caption2Medium,
            color = BbangZipTheme.colors.labelAssistive_282119_28
        )
        Text(
            text = data.studyContents,
            style = BbangZipTheme.typography.caption1Medium,
            color = BbangZipTheme.colors.labelAlternative_282119_61
        )
        Text(
            text = "${data.startPage}p - ${data.finishPage}p",
            style = BbangZipTheme.typography.label1Bold,
            color = BbangZipTheme.colors.labelNormal_282119
        )
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = BbangZipTheme.colors.statusDestructive_FF8345, shape = RoundedCornerShape(11.dp))
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(text = "D+5", style = BbangZipTheme.typography.caption1Medium, color = BbangZipTheme.colors.staticWhite_FFFFFF)
            }

            Text(
                text = "${data.deadline}까지",
                style = BbangZipTheme.typography.label1Bold,
                color = BbangZipTheme.colors.labelNormal_282119
            )
        }
    }
}