package org.android.bbangzip.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SplashScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(BbangZipTheme.colors.backgroundAccent_FFDAA0),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.img_login1),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    BBANGZIPTheme {
        SplashScreen()
    }
}
