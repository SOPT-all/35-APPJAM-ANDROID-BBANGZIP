package org.android.bbangzip.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import org.android.bbangzip.ui.theme.defaultBbangZipTypography

sealed class BbangZipButtonType(
    val enableBackgroundColor: Color,
    val enableContentColor: Color,
    val enableBorderColor: Color = Color.Unspecified,
    val disableBackgroundColor: Color = Color.Unspecified,
    val disableContentColor: Color = Color.Unspecified,
    val disableBorderColor: Color = Color.Unspecified,
    val borderWidth: Dp = 0.dp,
) {
    data object Solid : BbangZipButtonType(
        enableBackgroundColor = defaultBbangZipColors.primaryNormal_282119,
        enableContentColor = defaultBbangZipColors.staticWhite_FFFFFF,
        disableBackgroundColor = defaultBbangZipColors.interactionDisable_F5F5F5,
        disableContentColor = defaultBbangZipColors.labelDisable_282119_12,
    )

    data object Outlined : BbangZipButtonType(
        enableBackgroundColor = defaultBbangZipColors.staticWhite_FFFFFF,
        enableContentColor = defaultBbangZipColors.primaryNormal_282119,
        enableBorderColor = defaultBbangZipColors.lineStrong_68645E_52,
        disableBackgroundColor = Color.Unspecified,
        disableContentColor = Color.Unspecified,
        disableBorderColor = Color.Unspecified,
        borderWidth = 1.dp,
    )

    data object Kakao : BbangZipButtonType(
        enableBackgroundColor = defaultBbangZipColors.kakaoContainer_FEE500,
        enableContentColor = defaultBbangZipColors.kakaoLabel_000000_85,
    )
}

sealed class BbangZipButtonSize(
    val cornerRadius: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val iconSize: Dp,
    val spacing: Dp,
    val textStyle: TextStyle,
) {
    data object Large : BbangZipButtonSize(
        cornerRadius = 24.dp,
        horizontalPadding = 28.dp,
        verticalPadding = 16.dp,
        iconSize = 20.dp,
        spacing = 6.dp,
        textStyle = defaultBbangZipTypography.body1Bold,
    )

    data object Medium : BbangZipButtonSize(
        cornerRadius = 16.dp,
        horizontalPadding = 20.dp,
        verticalPadding = 9.dp,
        iconSize = 18.dp,
        spacing = 5.dp,
        textStyle = defaultBbangZipTypography.body2Bold,
    )
}
