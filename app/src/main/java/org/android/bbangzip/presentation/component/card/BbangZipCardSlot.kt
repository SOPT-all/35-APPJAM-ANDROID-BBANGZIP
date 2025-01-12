package org.android.bbangzip.presentation.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.component.card.model.BbangZipCardData
import org.android.bbangzip.presentation.type.BbangZipCardStateType
import org.android.bbangzip.presentation.type.BbangZipCardType
import org.android.bbangzip.presentation.type.ToDoCardStateType
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@Composable
fun BbangZipCardSlot(
    stateType: BbangZipCardStateType,
    studyType: BbangZipCardType,
    data: BbangZipCardData,
    modifier: Modifier = Modifier,
) {
    when (studyType) {
        BbangZipCardType.TODO -> {
            val todoDate = data as? BbangZipCardData.TodoCardData ?: throw IllegalArgumentException("Invalid data for TODO card")
            ToDoCard(
                stateType = stateType,
                data = todoDate,
                modifier = modifier,
            )
        }

        BbangZipCardType.SUBJECT -> {
            val subjectData = data as? BbangZipCardData.SubjectCardData ?: throw IllegalArgumentException("Invalid data for SUBJECT card")
            SubjectCard(
                stateType = stateType,
                data = subjectData,
                modifier = modifier,
            )
        }

        BbangZipCardType.FRIEND -> {
            val friendData = data as? BbangZipCardData.FriendCardData ?: throw IllegalArgumentException("Invalid data for FRIEND card")
            FriendCard(
                stateType = stateType,
                data = friendData,
                modifier = modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyCardSlotPreview() {
    BBANGZIPTheme {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            // ToDoCard States
            BbangZipCardSlot(
                stateType = BbangZipCardStateType.DEFAULT,
                studyType = BbangZipCardType.TODO,
                data =
                    BbangZipCardData.TodoCardData(
                        pieceId = "1",
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        remainingDays = 1,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            BbangZipCardSlot(
                stateType = BbangZipCardStateType.CHECKED,
                studyType = BbangZipCardType.TODO,
                data =
                    BbangZipCardData.TodoCardData(
                        pieceId = "1",
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        remainingDays = 1,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            BbangZipCardSlot(
                stateType = BbangZipCardStateType.CHECKABLE,
                studyType = BbangZipCardType.TODO,
                data =
                    BbangZipCardData.TodoCardData(
                        pieceId = "1",
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        remainingDays = 1,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            BbangZipCardSlot(
                stateType = ToDoCardStateType.COMPLETE,
                studyType = BbangZipCardType.TODO,
                data =
                    BbangZipCardData.TodoCardData(
                        pieceId = "1",
                        subjectName = "경제통계학개론",
                        examName = "중간고사",
                        studyContents = "경제통계학",
                        startPage = 36,
                        finishPage = 60,
                        deadline = "2025년 4월 25일",
                        remainingDays = 1,
                    ),
                modifier = Modifier.padding(bottom = 16.dp),
            )

            // SubjectCard States
            BbangZipCardSlot(
                stateType = BbangZipCardStateType.DEFAULT,
                studyType = BbangZipCardType.SUBJECT,
                data =
                    BbangZipCardData.SubjectCardData(
                        subjectId = 1,
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        examRemainingDays = 1,
                        pendingCount = 0,
                        inProgressCount = 6,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            BbangZipCardSlot(
                stateType = BbangZipCardStateType.CHECKED,
                studyType = BbangZipCardType.SUBJECT,
                data =
                    BbangZipCardData.SubjectCardData(
                        subjectId = 1,
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        examRemainingDays = 1,
                        pendingCount = 0,
                        inProgressCount = 6,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            BbangZipCardSlot(
                stateType = BbangZipCardStateType.CHECKABLE,
                studyType = BbangZipCardType.SUBJECT,
                data =
                    BbangZipCardData.SubjectCardData(
                        subjectId = 1,
                        subjectName = "[경영] 경제통계학",
                        examName = "중간고사",
                        examRemainingDays = 1,
                        pendingCount = 0,
                        inProgressCount = 6,
                    ),
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendCardPreview() {
    BBANGZIPTheme {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            // FriendCard States
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                // FriendCard States
                BbangZipCardSlot(
                    stateType = BbangZipCardStateType.DEFAULT,
                    studyType = BbangZipCardType.FRIEND,
                    data =
                        BbangZipCardData.FriendCardData(
                            friendId = 1,
                            friendName = "강라리",
                            imageUrl = "R.drawable.ic_person",
                        ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                BbangZipCardSlot(
                    stateType = BbangZipCardStateType.CHECKED,
                    studyType = BbangZipCardType.FRIEND,
                    data =
                        BbangZipCardData.FriendCardData(
                            friendId = 2,
                            friendName = "최민지",
                            imageUrl = "R.drawable.ic_person",
                        ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                BbangZipCardSlot(
                    stateType = BbangZipCardStateType.CHECKABLE,
                    studyType = BbangZipCardType.FRIEND,
                    data =
                        BbangZipCardData.FriendCardData(
                            friendId = 3,
                            friendName = "이은지",
                            imageUrl = "R.drawable.ic_person",
                        ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
        }
    }
}
