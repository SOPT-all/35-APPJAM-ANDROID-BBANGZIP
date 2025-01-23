package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.launch
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.model.Badge
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipGetBadgeBottomSheet(
    badgeList: List<Badge>,
    isBottomSheetVisible: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onClickCancelButton: () -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = { badgeList.size })
    val coroutineScope = rememberCoroutineScope()

    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.badge_get_bottom_sheet_title, badgeList.size),
                    style = BbangZipTheme.typography.heading2Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    BadgeDetailContent(
                        pagerState = pagerState,
                        badgeList = badgeList,
                    )

                    if (badgeList.size != 1 && pagerState.currentPage != 0) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_chevronleft_thick_small_24),
                            contentDescription = null,
                            tint = BbangZipTheme.colors.labelAssistive_282119_28,
                            modifier =
                                Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(8.dp)
                                    .applyFilterOnClick {
                                        if (pagerState.currentPage > 0) {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                            }
                                        }
                                    },
                        )
                    }

                    if (badgeList.size != 1 && pagerState.currentPage != badgeList.size - 1) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_chevronright_thick_small_24),
                            contentDescription = null,
                            tint = BbangZipTheme.colors.labelAssistive_282119_28,
                            modifier =
                                Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(8.dp)
                                    .applyFilterOnClick {
                                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                            }
                                        }
                                    },
                        )
                    }
                }
            }
        },
        cancelButton = {
            if (badgeList.size != 1) {
                BbangZipPagerIndicator(pagerState = pagerState)

                Spacer(modifier = Modifier.height(37.dp))
            } else {
                Spacer(modifier = Modifier.height(48.dp))
            }

            BbangZipButton(
                modifier = Modifier.fillMaxWidth(),
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onClickCancelButton() },
                label = stringResource(R.string.btn_close_label),
            )
        },
    )
}

@Composable
private fun BadgeHashTag(
    hashTag: List<String>,
    modifier: Modifier = Modifier,
) {
    if (hashTag.size == 2) {
        hashTag.onEachIndexed { index, s ->
            Text(
                text = s,
                style = BbangZipTheme.typography.label1Small,
                color = BbangZipTheme.colors.labelAlternative_282119_61,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 1.dp),
            )
        }
    } else {
        Text(
            text = hashTag[0],
            style = BbangZipTheme.typography.label1Small,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = hashTag[1] + " " + hashTag[2],
            style = BbangZipTheme.typography.label1Small,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BbangZipPagerIndicator(
    pagerState: PagerState,
) {
    Row(
        Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) BbangZipTheme.colors.materialDimmer_282119_52 else BbangZipTheme.colors.materialDimmer_282119_52.copy(alpha = 0.16f)
            Box(
                modifier =
                    Modifier
                        .padding(4.dp)
                        .background(color, CircleShape)
                        .size(6.dp),
            )
        }
    }
}

@Composable
private fun BadgeDetailContent(
    pagerState: PagerState,
    badgeList: List<Badge>,
    modifier: Modifier = Modifier,
) {
    if (badgeList.size == 1) {
        Column(
            modifier =
                modifier
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = badgeList[0].badgeImg)
                        .crossfade(enable = true)
                        .build(),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = badgeList[0].badgeName,
                style = BbangZipTheme.typography.headline1Bold,
                color = BbangZipTheme.colors.labelNormal_282119,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(25.dp))

            BadgeHashTag(hashTag = badgeList[0].hashTags)
        }
    } else {
        HorizontalPager(
            state = pagerState,
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) { page ->
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = badgeList[page].badgeImg)
                            .crossfade(enable = true)
                            .build(),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = badgeList[page].badgeName,
                    style = BbangZipTheme.typography.headline1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(25.dp))

                BadgeHashTag(hashTag = badgeList[page].hashTags)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BbangZipGetBadgeBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }

    BbangZipGetBadgeBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
        badgeList =
            listOf(
                Badge(
                    badgeName = "시작이 빵이다",
                    badgeImg = "https://cdn-icons-png.flaticon.com/512/5899/5899666.png",
                    hashTags = listOf("#해시태그 첫번째", "#해시태그 두번째", "#해시태그 세번째"),
                ),
                Badge(
                    badgeName = "시작이 빵이다2",
                    badgeImg = "https://cdn-icons-png.flaticon.com/512/5899/5899666.png",
                    hashTags = listOf("#해시태그 첫번째", "#해시태그 두번째", "#해시태그 세번째"),
                ),
                Badge(
                    badgeName = "시작이 빵이다3",
                    badgeImg = "https://cdn-icons-png.flaticon.com/512/5899/5899666.png",
                    hashTags = listOf("#해시태그 첫번째", "#해시태그 두번째", "#해시태그 세번째"),
                ),
            ),
        onClickCancelButton = { isBottomSheetVisible = !isBottomSheetVisible },
    )
}
