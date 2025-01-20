package org.android.bbangzip.presentation.ui.onboarding.onboardingend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun OnboardingEndScreen(
    onClickNextBtn: () -> Unit = {},
    onBackBtnClick: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF),
    ) {
        BbangZipBaseTopBar(
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            onLeadingIconClick = { onBackBtnClick() },
        )

        Text(
            text = stringResource(id = R.string.onboarding_final_title),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .padding(top = (LocalConfiguration.current.screenHeightDp * 0.184).dp),
            style = BbangZipTheme.typography.title2Bold,
            color = BbangZipTheme.colors.labelNormal_282119,
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.img_login2),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onClickNextBtn() },
            label = stringResource(R.string.btn_finish_onboarding_label),
            trailingIcon = R.drawable.ic_chevronright_thick_small_24,
        )
    }
}
