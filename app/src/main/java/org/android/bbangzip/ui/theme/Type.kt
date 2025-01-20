package org.android.bbangzip.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.android.bbangzip.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold))
val PretendardSmall = FontFamily(Font(R.font.pretendard_small))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))

@Immutable
data class BbangZipTypography(
    // Title 1
    val title1Bold: TextStyle,
    val title1Medium: TextStyle,
    val title1Small: TextStyle,
    // Title 2
    val title2Bold: TextStyle,
    val title2Medium: TextStyle,
    val title2Small: TextStyle,
    // Title 3
    val title3Bold: TextStyle,
    val title3Medium: TextStyle,
    val title3Small: TextStyle,
    // Heading 1
    val heading1Bold: TextStyle,
    val heading1Medium: TextStyle,
    val heading1Small: TextStyle,
    // Heading 2
    val heading2Bold: TextStyle,
    val heading2Medium: TextStyle,
    val heading2Small: TextStyle,
    // Headline 1
    val headline1Bold: TextStyle,
    val headline1Medium: TextStyle,
    val headline1Small: TextStyle,
    // Headline 2
    val headline2Bold: TextStyle,
    val headline2Medium: TextStyle,
    val headline2Small: TextStyle,
    // Body 1
    val body1Bold: TextStyle,
    val body1Medium: TextStyle,
    val body1Small: TextStyle,
    // Body 2
    val body2Bold: TextStyle,
    val body2Medium: TextStyle,
    val body2Small: TextStyle,
    // Label 1
    val label1Bold: TextStyle,
    val label1Medium: TextStyle,
    val label1Small: TextStyle,
    // Label 2
    val label2Bold: TextStyle,
    val label2Medium: TextStyle,
    val label2Small: TextStyle,
    // Caption 1
    val caption1Bold: TextStyle,
    val caption1Medium: TextStyle,
    val caption1Small: TextStyle,
    // Caption 2
    val caption2Bold: TextStyle,
    val caption2Medium: TextStyle,
    val caption2Small: TextStyle,
)

val defaultBbangZipTypography =
    BbangZipTypography(
        // Title 1
        title1Bold =
            TextStyle(
                fontSize = 36.sp,
                lineHeight = 48.sp,
                letterSpacing = (-0.027).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        title1Medium =
            TextStyle(
                fontSize = 36.sp,
                lineHeight = 48.sp,
                letterSpacing = (-0.027).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        title1Small =
            TextStyle(
                fontSize = 36.sp,
                lineHeight = 48.sp,
                letterSpacing = (-0.027).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Title 2
        title2Bold =
            TextStyle(
                fontSize = 28.sp,
                lineHeight = 38.sp,
                letterSpacing = (-0.0236).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        title2Medium =
            TextStyle(
                fontSize = 28.sp,
                lineHeight = 38.sp,
                letterSpacing = (-0.0236).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        title2Small =
            TextStyle(
                fontSize = 28.sp,
                lineHeight = 38.sp,
                letterSpacing = (-0.0236).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Title 3
        title3Bold =
            TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = (-0.023).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        title3Medium =
            TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = (-0.023).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        title3Small =
            TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = (-0.023).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Heading 1
        heading1Bold =
            TextStyle(
                fontSize = 22.sp,
                lineHeight = 30.sp,
                letterSpacing = (-0.0194).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        heading1Medium =
            TextStyle(
                fontSize = 22.sp,
                lineHeight = 30.sp,
                letterSpacing = (-0.0194).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        heading1Small =
            TextStyle(
                fontSize = 22.sp,
                lineHeight = 30.sp,
                letterSpacing = (-0.0194).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Heading 2
        heading2Bold =
            TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.012).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        heading2Medium =
            TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.012).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        heading2Small =
            TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.012).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Headline 1
        headline1Bold =
            TextStyle(
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = (-0.002).em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        headline1Medium =
            TextStyle(
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = (-0.002).em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        headline1Small =
            TextStyle(
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = (-0.002).em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Headline 2
        headline2Bold =
            TextStyle(
                fontSize = 17.sp,
                lineHeight = 24.sp,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        headline2Medium =
            TextStyle(
                fontSize = 17.sp,
                lineHeight = 24.sp,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        headline2Small =
            TextStyle(
                fontSize = 17.sp,
                lineHeight = 24.sp,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Body 1
        body1Bold =
            TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.0057.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        body1Medium =
            TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.0057.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        body1Small =
            TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.0057.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Body 2
        body2Bold =
            TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.0096.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        body2Medium =
            TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.0096.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        body2Small =
            TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.0096.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Label 1
        label1Bold =
            TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.0145.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        label1Medium =
            TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.0145.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        label1Small =
            TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.0145.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Label 2
        label2Bold =
            TextStyle(
                fontSize = 13.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.0194.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        label2Medium =
            TextStyle(
                fontSize = 13.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.0194.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        label2Small =
            TextStyle(
                fontSize = 13.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.0194.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Caption 1
        caption1Bold =
            TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.0252.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        caption1Medium =
            TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.0252.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        caption1Small =
            TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.0252.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
        // Caption 2
        caption2Bold =
            TextStyle(
                fontSize = 11.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.0311.em,
                fontFamily = PretendardBold,
            ).withoutFontPadding(),
        caption2Medium =
            TextStyle(
                fontSize = 11.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.0311.em,
                fontFamily = PretendardMedium,
            ).withoutFontPadding(),
        caption2Small =
            TextStyle(
                fontSize = 11.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.0311.em,
                fontFamily = PretendardSmall,
            ).withoutFontPadding(),
    )

val LocalBbangZipTypography = staticCompositionLocalOf { defaultBbangZipTypography }

fun TextStyle.withoutFontPadding(): TextStyle {
    return this.copy(platformStyle = PlatformTextStyle(includeFontPadding = false))
}
