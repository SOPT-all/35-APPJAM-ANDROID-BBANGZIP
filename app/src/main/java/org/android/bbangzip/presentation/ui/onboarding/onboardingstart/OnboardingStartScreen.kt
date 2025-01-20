package org.android.bbangzip.presentation.ui.onboarding.onboardingstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun OnboardingStartScreen(
    onClickNextBtn: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_start_title),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = (LocalConfiguration.current.screenHeightDp * 0.184).dp),
            style = BbangZipTheme.typography.title2Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_onboarding_start),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            modifier = Modifier.fillMaxWidth(),
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onClickNextBtn() },
            label = stringResource(R.string.btn_start_app_label),
            trailingIcon = R.drawable.ic_chevronright_thick_small_24,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingStartScreenPreview() {
    BBANGZIPTheme {
    }
}
