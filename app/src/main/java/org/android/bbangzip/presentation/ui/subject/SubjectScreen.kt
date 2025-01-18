package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.AddSubjectCard
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.SubjectCard
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SubjectScreen(
    modifier: Modifier = Modifier,
) {
    /**
     * 처음에 받는 카드들의 state는 default
     * 온보딩 후 처음 진입 시 cardViewType -> DEFAULT
     **/

    /**
     * 쓰레기통 버튼 누를 시 cardViewType -> DELETE
     * X 버튼 누를 시 cardViewType -> DEFAULT
     * subjects.size == 0 일 때 cardViewType -> EMPTY
     **/
    val cardViewType = CardViewType.EMPTY
    val subjects: List<SubjectCardModel> =
        listOf(
            SubjectCardModel(
                subjectName = "경제통계학",
                examName = "",
                pendingCount = 0,
                inProgressCount = 6,
                subjectId = 1,
                examRemainingDays = 1,
                state = BbangZipCardState.DEFAULT
            ),
            SubjectCardModel(
                subjectName = "[경영] 경제통계학",
                examName = "중간고사",
                pendingCount = 0,
                inProgressCount = 6,
                subjectId = 1,
                examRemainingDays = 1,
                state = BbangZipCardState.DEFAULT
            ),
            SubjectCardModel(
                subjectName = "[경영] 경제통계학",
                examName = "중간고사",
                pendingCount = 0,
                inProgressCount = 6,
                subjectId = 1,
                examRemainingDays = 1,
                state = BbangZipCardState.DEFAULT
            ),
            SubjectCardModel(
                subjectName = "[경영] 경제통계학",
                examName = "중간고사",
                pendingCount = 0,
                inProgressCount = 6,
                subjectId = 1,
                examRemainingDays = 1,
                state = BbangZipCardState.DEFAULT
            ),
            SubjectCardModel(
                subjectName = "[경영] 경제통계학",
                examName = "중간고사",
                pendingCount = 0,
                inProgressCount = 6,
                subjectId = 1,
                examRemainingDays = 1,
                state = BbangZipCardState.DEFAULT
            ),
        )

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val backgroundHeight = (screenHeightDp * 0.32).toInt()

    LazyColumn(
        modifier =
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = BbangZipTheme.colors.staticWhite_FFFFFF)
        ,
    ) {
        item {
            Box(
                modifier =
                modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(backgroundHeight.dp)
                    .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0, shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            ) {
                Row(
                    modifier = modifier
                        .padding(start = 4.dp, top = 18.dp)
                        .applyFilterOnClick(
                            radius = 16.dp,
                            isDisabled = false,
                            onClick = { }
                        )
                        .padding(vertical = 8.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "2025년 2학기",
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )

                    Gap(width = 4)

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevrondown_small_24),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .size(size = 20.dp),
                    )
                }
            }
        }

        // 아이콘에 padding을 8dp 씩 주면서 갭의 수치가 피그마와 다를 수 있습니다!
        // 위아래 8씩 빼주었다고 보면 됩니다.
        item {
            Gap(height = 40)
        }

        item {
            when(cardViewType){
                CardViewType.DEFAULT -> DefaultCardView(
                    modifier = modifier,
                    subjects = subjects
                )
                CardViewType.DELETE -> DeleteCardView(
                    modifier = modifier,
                    subjects = subjects
                )
                CardViewType.EMPTY -> EmptySubjectCardView()
            }
        }
        item{
            Gap(height = 84)
        }
    }
}

@Composable
private fun DefaultCardView(
    modifier: Modifier,
    subjects: List<SubjectCardModel>,
) {
    Column {
        Row(
            modifier = Modifier.padding(start = 24.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "어떤 과목을 공부해 볼까요?",
                style = BbangZipTheme.typography.headline2Bold,
                color = BbangZipTheme.colors.labelAlternative_282119_61,
            )

            Gap()

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_trash_default_24),
                contentDescription = null,
                modifier = modifier
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                    ) { }
                    .padding(8.dp)
            )
        }

        Gap(24)

        if (subjects.size % 2 == 1) {
            for (i in 0 until (subjects.size + 1) / 2) {
                if (i == (subjects.size + 1) / 2 - 1) {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        SubjectCard(
                            data = subjects.last()
                        )
                        AddSubjectCard()
                    }
                } else {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        for (j in i * 2 until (i + 1) * 2) {
                            SubjectCard(data = subjects[j])
                        }
                    }
                }
            }
        } else {
            for (i in 0 until (subjects.size + 1) / 2 + 1) {
                if (i == (subjects.size + 1) / 2) {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        AddSubjectCard()
                    }
                } else {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        for (j in i * 2 until (i + 1) * 2) {
                            SubjectCard(data = subjects[j])
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DeleteCardView(
    modifier: Modifier,
    subjects: List<SubjectCardModel>,
) {
    Column {
        Row(
            modifier = Modifier.padding(start = 24.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "어떤 과목을 공부해 볼까요?",
                style = BbangZipTheme.typography.headline2Bold,
                color = BbangZipTheme.colors.labelAlternative_282119_61,
            )

            Gap()

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_trash_default_24),
                contentDescription = null,
                modifier = modifier
                    .applyFilterOnClick(
                        radius = 20.dp,
                        isDisabled = false,
                    ) { }
                    .padding(8.dp)
            )
        }

        Gap(24)

        if (subjects.size % 2 == 1) {
            for (i in 0 until (subjects.size + 1) / 2) {
                if (i == (subjects.size + 1) / 2 - 1) {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        SubjectCard(data = subjects.last().copy(state = BbangZipCardState.CHECKABLE))
                    }
                } else {
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        for (j in i * 2 until (i + 1) * 2) {
                            SubjectCard(data = subjects[j].copy(state = BbangZipCardState.CHECKABLE))
                        }
                    }
                }
            }
        } else {
            for (i in 0 until subjects.size/2 + 1) {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    for (j in i * 2 until (i + 1) * 2) {
                        SubjectCard(data = subjects[j].copy(state = BbangZipCardState.CHECKABLE))
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySubjectCardView(
    modifier: Modifier = Modifier,
    onAddSubjectButtonClicked: () -> Unit = {}
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(328.dp)
                .background(color = BbangZipTheme.colors.backgroundAlternative_F5F5F5, shape = RoundedCornerShape(size = 32.dp)), contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.empty_view_text))
        }

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onAddSubjectButtonClicked() },
            label = stringResource(R.string.btn_add_subject_label),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = R.drawable.ic_plus_thick_24,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SubjectScreenPreview() {
    BBANGZIPTheme {
        SubjectScreen()
    }
}
