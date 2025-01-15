package org.android.bbangzip.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors

enum class BbangZipCardType(
    val backgroundColor: Color,
    val borderColor: Color,
    val borderWidth: Dp,
    val radius: Dp = 24.dp,
    val shadowOptions: BbangZipShadowType = BbangZipShadowType.EMPTY,
    val checkBoxBackgroundColor: Color = defaultBbangZipColors.fillStrong_68645E_16,
) {
    DEFAULT(
        backgroundColor = defaultBbangZipColors.backgroundNormal_FFFFFF,
        borderColor = defaultBbangZipColors.lineAlternative_68645E_08,
        borderWidth = 1.dp,
        shadowOptions = BbangZipShadowType.Emphasize,
    ),
    CHECKABLE(
        backgroundColor = defaultBbangZipColors.backgroundAlternative_F5F5F5,
        borderColor = defaultBbangZipColors.lineAlternative_68645E_08,
        borderWidth = 1.dp,
        shadowOptions = BbangZipShadowType.Strong,
    ),
    CHECKED(
        backgroundColor = defaultBbangZipColors.backgroundAlternative_F5F5F5,
        borderColor = defaultBbangZipColors.lineStrong_68645E_52,
        borderWidth = 3.dp,
        shadowOptions = BbangZipShadowType.Emphasize,
    ),
    COMPLETE(
        backgroundColor = defaultBbangZipColors.backgroundNormal_FFFFFF,
        borderColor = defaultBbangZipColors.lineAlternative_68645E_08,
        borderWidth = 1.dp,
        checkBoxBackgroundColor = defaultBbangZipColors.secondaryNormal_FFCD80
    ),
}
