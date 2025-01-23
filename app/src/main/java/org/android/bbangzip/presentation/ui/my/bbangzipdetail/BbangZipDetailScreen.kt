package org.android.bbangzip.presentation.ui.my.bbangzipdetail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BbangZip
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

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
            .fillMaxSize()
    ) {
        BbangZipBaseTopBar(
            backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            onLeadingIconClick = { popBackStack() },
            title = stringResource(R.string.my_bbangzip)
        )

        BbangZipPager(
            modifier = Modifier.fillMaxSize(),
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
    Timber.tag("[마이페이지] 데이터 로드 -> ").d("$bbangZipList")

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .height(height = (LocalConfiguration.current.screenHeightDp * 0.534).dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0),
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
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
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
                }

                if(!bbangZipList[page].isLocked) {
                    Spacer(modifier = Modifier.height(78.dp))

                    Text(
                        text = bbangZipList[page].description,
                        style = BbangZipTheme.typography.headline1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Spacer(modifier = Modifier.height(62.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_lock_default_28),
                            contentDescription = null,
                            tint = BbangZipTheme.colors.lineStrong_68645E_52,
                            modifier = Modifier.height(70.dp).aspectRatio(0.714f).alpha(0.52f)
                        )

                        Text(
                            text = stringResource(R.string.my_bbangzip_lock),
                            textAlign = TextAlign.Center,
                            style = BbangZipTheme.typography.body1Bold,
                            color = BbangZipTheme.colors.labelAlternative_282119_61
                        )
                    }
                }
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