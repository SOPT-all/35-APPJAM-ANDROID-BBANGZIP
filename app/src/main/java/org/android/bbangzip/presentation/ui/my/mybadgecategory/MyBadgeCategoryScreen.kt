package org.android.bbangzip.presentation.ui.my.mybadgecategory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BadgeCategory
import org.android.bbangzip.presentation.model.BadgeDetail
import org.android.bbangzip.presentation.ui.my.mybadgecategory.component.BbangZipBadgeDetailBottomSheet
import org.android.bbangzip.presentation.ui.my.mybadgecategory.component.LockedBadgeImage
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyBadgeCategoryScreen(
    badgeCategoryState: MyBadgeCategoryContract.MyBadgeCategoryState,
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit = {},
    onBadgeCardClicked: (String) -> Unit = {},
    onBadgeDetailBottomSheetDismissRequest: () -> Unit = {},
    onBadgeDetailBottomSheetDismissButtonClicked: () -> Unit = {},
) {
    val scrollState = rememberLazyListState()
    val isShadowed by remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 0
        }
    }
    LazyColumn(
        modifier =
            modifier
                .fillMaxWidth()
                .background(BbangZipTheme.colors.staticWhite_FFFFFF),
        state = scrollState,
    ) {
        stickyHeader {
            BbangZipBaseTopBar(
                title = stringResource(R.string.badge_category_top_bar_title),
                leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
                onLeadingIconClick = onBackIconClicked,
                backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                isShadowed = isShadowed,
            )
        }

        item {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                            shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp),
                        ),
            ) {
                Text(
                    text = stringResource(R.string.badge_category_title, "밍밍"),
                    style = BbangZipTheme.typography.heading2Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                    modifier =
                        Modifier
                            .padding(start = 24.dp, top = 28.dp, bottom = 48.dp),
                )
            }

            Spacer(Modifier.height(48.dp))
        }

        item {
            BadgeCategoryGridList(
                badgeCategoryList = badgeCategoryState.badgeCategoryList1,
                categoryDescription = "이번 학기 빵점 탈출 내가 해냄!",
                onBadgeCardClicked = onBadgeCardClicked,
            )

            Spacer(Modifier.height(64.dp))
        }

        item {
            BadgeCategoryGridList(
                badgeCategoryList = badgeCategoryState.badgeCategoryList2,
                categoryDescription = "지금 바로 시작하면 미룬이 탈출 가능!",
                onBadgeCardClicked = onBadgeCardClicked,
            )
        }
    }

    BbangZipBadgeDetailBottomSheet(
        badgeDetail = badgeCategoryState.badgeDetail,
        isBottomSheetVisible = badgeCategoryState.badgeDetailBottomSheetState,
        cancelButtonText = "닫기",
        onDismissRequest = onBadgeDetailBottomSheetDismissRequest,
        onClickCancelButton = onBadgeDetailBottomSheetDismissButtonClicked,
    )
}

@Composable
fun BadgeCategoryGridList(
    badgeCategoryList: List<BadgeCategory>,
    categoryDescription: String,
    modifier: Modifier = Modifier,
    onBadgeCardClicked: (String) -> Unit = {},
) {
    val badgeCategoryTitle = badgeCategoryList.first().categoryName
    val badgeHeight = LocalConfiguration.current.screenHeightDp / 10

    Column(
        modifier =
            modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(BbangZipTheme.colors.staticWhite_FFFFFF),
    ) {
        Text(
            text = badgeCategoryTitle,
            style = BbangZipTheme.typography.title3Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
            modifier = Modifier.padding(start = 8.dp),
        )

        Text(
            text = categoryDescription,
            style = BbangZipTheme.typography.label1Bold,
            color = BbangZipTheme.colors.labelAlternative_282119_61,
            modifier = Modifier.padding(start = 8.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .heightIn(max = (badgeHeight * (badgeCategoryList.size - 1 / 3 + 1) + 20 * (badgeCategoryList.size / 3)).dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(badgeCategoryList.size) { index ->
                Box(
                    modifier =
                        Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { onBadgeCardClicked(badgeCategoryList[index].name) },
                    contentAlignment = Alignment.Center,
                ) {
                    if (badgeCategoryList[index].isLocked) {
                        LockedBadgeImage(
                            imgUrl = badgeCategoryList[index].imageUrl,
                        )
                    } else {
                        AsyncImage(
                            model = badgeCategoryList[index].imageUrl,
                            contentDescription = null,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(24.dp)),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyBadgeCategoryScreenPreview() {
    val sampleBadgeCategoryList1 =
        listOf(
            BadgeCategory(
                name = "Badge 1",
                categoryName = "Category 1",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 2",
                categoryName = "Category 1",
                isLocked = false,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 3",
                categoryName = "Category 1",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
        )

    val sampleBadgeCategoryList2 =
        listOf(
            BadgeCategory(
                name = "Badge 4",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 5",
                categoryName = "Category 2",
                isLocked = false,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
            BadgeCategory(
                name = "Badge 6",
                categoryName = "Category 2",
                isLocked = true,
                imageUrl = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true",
            ),
        )

    val sampleState =
        MyBadgeCategoryContract.MyBadgeCategoryState(
            badgeCategoryList1 = sampleBadgeCategoryList1,
            badgeCategoryList2 = sampleBadgeCategoryList2,
            badgeDetailBottomSheetState = false,
            badgeDetail = BadgeDetail(),
        )

    MyBadgeCategoryScreen(
        badgeCategoryState = sampleState,
        onBackIconClicked = { println("Back icon clicked") },
        onBadgeCardClicked = { badgeName -> println("Badge clicked: $badgeName") },
        onBadgeDetailBottomSheetDismissRequest = { println("BottomSheet dismissed") },
        onBadgeDetailBottomSheetDismissButtonClicked = { println("Dismiss button clicked") },
    )
}
