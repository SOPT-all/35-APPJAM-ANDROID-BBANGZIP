package org.android.bbangzip.presentation.component.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.ShadowConfig
import org.android.bbangzip.presentation.type.StudyCardData
import org.android.bbangzip.presentation.type.StudyCardStateType
import org.android.bbangzip.presentation.type.StudyCardStyle
import org.android.bbangzip.presentation.type.StudyCardType
import org.android.bbangzip.presentation.type.ToDoCardStateType
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun StudyCardSlot(
    stateType: StudyCardStateType,
    studyType: StudyCardType,
    data: StudyCardData,
    modifier: Modifier = Modifier,
) {
    val style =stateType.getStyle()
    when (studyType) {
        StudyCardType.TODO -> {
            val todoDate = data as? StudyCardData.TodoCardData ?: throw IllegalArgumentException("Invalid data for TODO card")

                ToDoCard(
                    style = style,
                    data = todoDate,
                    isComplete = stateType == ToDoCardStateType.COMPLETE,
                    modifier = modifier
                )
        }

        StudyCardType.SUBJECT -> {
            val subjectData = data as? StudyCardData.SubjectCardData ?: throw IllegalArgumentException("Invalid data for SUBJECT card")
            SubjectCard(
                style = style,
                data = subjectData,
                modifier = modifier
            )
        }
    }
}
/*
    공통 부분 : State에따라 background border 그림자 변경되는거
    state type = Catched / catchAbLe / default / Complete(버튼에서만)
    state에따라서 background opacity 를 다르게 하고 icon 차이
    card type = todo / subject
    카드의 높이 넓이가 아예 다름
    들어가는 data도 다름
    분기도 다름
    그림자도 다fma
    바깥 state를 정했잖아 안의 내용물은 cardSTate로 정해보자


 */