package org.android.bbangzip.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import okhttp3.internal.immutableListOf
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.balloon.BottomTailBalloon
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun LoginScreen(
    state: LoginContract.LoginState,
    pagerState: PagerState,
    onClickKakaoLoginBtn: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
        ) {
            PagerWithTitle(
                pagerState = pagerState,
                onboardingList = state.onBoardingList
            )

            Image(
                painter = painterResource(R.drawable.ic_background_circle_accent),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            PagerIndicator(pagerState = pagerState)
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            BottomTailBalloon(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.login_ballon_label),
                horizontalPadding = 82.dp,
            )

            BbangZipButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                bbangZipButtonType = BbangZipButtonType.Kakao,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { onClickKakaoLoginBtn() },
                label = stringResource(R.string.btn_kakao_login_label),
                leadingIcon = R.drawable.ic_kakao_default_24
            )
        }
    }
}

@Composable
private fun PagerWithTitle(
    pagerState: PagerState,
    onboardingList: List<Int>
) {
    Column(
        modifier = Modifier
            .height((LocalConfiguration.current.screenHeightDp * 0.59).dp)
            .background(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
    ) {
        Text(
            text = stringResource(R.string.login_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 84.dp, end = 20.dp),
            style = BbangZipTheme.typography.title2Bold,
            color = BbangZipTheme.colors.labelNormal_282119
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(onboardingList[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun PagerIndicator(
    pagerState: PagerState
) {
    Row(
        Modifier
            .padding(top = 19.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) BbangZipTheme.colors.materialDimmer_282119_52 else BbangZipTheme.colors.materialDimmer_282119_52.copy(alpha = 0.16f)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(color, CircleShape)
                    .size(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BBANGZIPTheme {
        LoginScreen(
            state = LoginContract.LoginState(
                isOnboardingCompleted = false,
                onBoardingList = immutableListOf(
                    R.drawable.img_login1,
                    R.drawable.img_login2,
                    R.drawable.img_login1
                )
            ),
            pagerState = rememberPagerState(pageCount = { 3 })
        )
    }
}