package org.android.bbangzip.presentation.ui.splash

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import org.android.bbangzip.R
import org.android.bbangzip.presentation.util.graphic.Gap
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SplashScreen() {
    (LocalView.current.context as Activity).window.statusBarColor = BbangZipTheme.colors.splashBackground_FFF9EF.toArgb()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbangZipTheme.colors.splashBackground_FFF9EF),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.img_bbangzip_logo),
                contentDescription = null
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_bbangzip_circle_t_12),
                contentDescription = null,
                tint = BbangZipTheme.colors.splashLogo_886759
            )

            Gap(3)

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_bbangzip_logo_name),
                contentDescription = null,
                tint = BbangZipTheme.colors.splashLogo_886759
            )
        }

        Gap(20)
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    BBANGZIPTheme {
        SplashScreen()
    }
}
