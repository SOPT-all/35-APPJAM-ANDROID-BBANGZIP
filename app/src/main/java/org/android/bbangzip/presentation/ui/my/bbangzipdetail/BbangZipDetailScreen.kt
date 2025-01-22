package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.app.Activity
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BbangZip
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipDetailScreen(
    state: BbangZipDetailContract.BbangZipDetailState,
    pagerState: PagerState,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
    (LocalView.current.context as Activity).window.statusBarColor = BbangZipTheme.colors.backgroundAccent_FFDAA0.toArgb()

    Column(
        modifier = modifier
    ) {
        BbangZipBaseTopBar(
            backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            onLeadingIconClick = { popBackStack() },
            title = stringResource(R.string.my_bbangzip)
        )

        BbangZipPager(
            pagerState = pagerState,
            bbangZipList = state.bbangZipList
        )
    }
}

@Composable
private fun BbangZipPager(
    pagerState: PagerState,
    bbangZipList: List<BbangZip>,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
    ) { page ->
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
                .height(height = (LocalConfiguration.current.screenHeightDp * 0.534).dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (bbangZipList[page].isLocked) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data = bbangZipList[page].imageUrl)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colorFilter = ColorFilter.tint(color = BbangZipTheme.colors.staticBlack_000000)
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data = bbangZipList[page].imageUrl)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }

            BbangZipPagerIndicator(pagerState = pagerState)

            Spacer(modifier = Modifier.height(24.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BbangZipChip(
                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                    text = "Lv " + bbangZipList[page].level
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = bbangZipList[page].name,
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119
                )

                Spacer(modifier = Modifier.width(78.dp))

                Text(
                    text = bbangZipList[page].description,
                    style = BbangZipTheme.typography.headline1Bold,
                    color = BbangZipTheme.colors.labelNormal_282119
                )
            }
        }
    }
}

@Composable
private fun BbangZipPagerIndicator(
    pagerState: PagerState
) {
    Row(
        Modifier
            .padding(top = 19.dp)
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