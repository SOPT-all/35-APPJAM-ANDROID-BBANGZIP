package org.android.bbangzip.presentation.ui.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import okhttp3.internal.immutableListOf
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.AddSubjectCard
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
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    subjects: List<SubjectCardModel>,
    cardViewType: CardViewType,
    deletedSet : Set<Int>,
    onClickTrashBtn: () -> Unit = {},
    onClickCancleBtn: () -> Unit = {},
    onClickDeleteModeCard: (Int) -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val bottomBarPadding = padding.calculateBottomPadding()
    val backgroundHeight = (screenHeightDp * 0.32).toInt()

    Box {
        LazyColumn(
            modifier =
            Modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
        ) {
            item {
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth()
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
                when (cardViewType) {
                    CardViewType.DEFAULT -> DefaultCardView(
                        modifier = modifier,
                        subjects = subjects,
                        onTrashIconClick = onClickTrashBtn
                    )

                    CardViewType.DELETE -> DeleteCardView(
                        modifier = modifier,
                        subjects = subjects,
                        onDeleteModeCardClick = onClickDeleteModeCard,
                        onCancleClick = onClickCancleBtn,
                        bottomPadding = bottomBarPadding
                    )

                    CardViewType.EMPTY -> EmptySubjectCardView()
                }
            }
        }
        if (cardViewType == CardViewType.DELETE) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 80.dp, start = 16.dp, end = 16.dp)
            ){
                BbangZipButton(
                    bbangZipButtonType = BbangZipButtonType.Solid,
                    bbangZipButtonSize = BbangZipButtonSize.Large,
                    onClick = { onClickCancleBtn() },
                    modifier = Modifier.fillMaxWidth(),
                    label = String.format(stringResource(R.string.btn_delete_label), deletedSet.size),
                    trailingIcon = R.drawable.ic_trash_default_24,
                    isEnable = deletedSet.isNotEmpty()
                )
            }
        }
    }
}

@Composable
private fun DefaultCardView(
    modifier: Modifier,
    subjects: List<SubjectCardModel>,
    onTrashIconClick: () -> Unit = {},
) {
    Column {
        Row(
            modifier = Modifier.padding(start = 24.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.add_subject_title),
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
                    ) {
                        onTrashIconClick()
                    }
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
        Gap(height = 84)
    }
}

@Composable
private fun DeleteCardView(
    modifier: Modifier,
    subjects: List<SubjectCardModel>,
    bottomPadding: Dp,
    onDeleteModeCardClick: (Int) -> Unit = {},
    onCancleClick: () -> Unit = {}
) {
    Box {
        Column {
            Row(
                modifier = Modifier.padding(start = 24.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.delete_subject_title),
                    style = BbangZipTheme.typography.headline2Bold,
                    color = BbangZipTheme.colors.labelAlternative_282119_61,
                )

                Gap()

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_x_small_24),
                    contentDescription = null,
                    modifier = modifier
                        .applyFilterOnClick(
                            radius = 20.dp,
                            isDisabled = false,
                        ) {
                            onCancleClick()
                        }
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
                                data = subjects.last(),
                                onClick = { index ->
                                    onDeleteModeCardClick(index)
                                }
                            )
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
                                SubjectCard(
                                    data = subjects[j],
                                    onClick = { index ->
                                        onDeleteModeCardClick(index)
                                    }
                                )
                            }
                        }
                    }
                }
            } else {
                for (i in 0 until subjects.size / 2 + 1) {
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
                            SubjectCard(
                                data = subjects[j],
                                onClick = { index ->
                                    onDeleteModeCardClick(index)
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(bottomPadding))

            Gap(height = 92)
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
        SubjectScreen(
            subjects = immutableListOf(
                SubjectCardModel(
                    subjectName = "경제통계학",
                    examName = "",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 1,
                    examRemainingDays = 1
                ),
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 2,
                    examRemainingDays = 1
                ),
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 3,
                    examRemainingDays = 1
                ),
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 4,
                    examRemainingDays = 1
                ),
                SubjectCardModel(
                    subjectName = "[경영] 경제통계학",
                    examName = "중간고사",
                    pendingCount = 0,
                    inProgressCount = 6,
                    subjectId = 5,
                    examRemainingDays = 1
                )
            ),
            cardViewType = CardViewType.DEFAULT,
            deletedSet = setOf(),
            padding = PaddingValues(64.dp)
        )
    }
}
