package org.andsopt.android.bbangzip.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.android.bbangzip.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
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

val defaultBbangZipTypography = BbangZipTypography(
    // Title 1
    title1Bold = TextStyle(
        fontSize = 36.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.027).em,
        fontFamily = PretendardBold
    ),
    title1Medium = TextStyle(
        fontSize = 36.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.027).em,
        fontFamily = PretendardMedium
    ),
    title1Small = TextStyle(
        fontSize = 36.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.027).em,
        fontFamily = PretendardSmall
    ),

    // Title 2
    title2Bold = TextStyle(
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.0236).em,
        fontFamily = PretendardBold
    ),
    title2Medium = TextStyle(
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.0236).em,
        fontFamily = PretendardMedium
    ),
    title2Small = TextStyle(
        fontSize = 28.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.0236).em,
        fontFamily = PretendardSmall
    ),

    // Title 3
    title3Bold = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.023).em,
        fontFamily = PretendardBold
    ),
    title3Medium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.023).em,
        fontFamily = PretendardMedium
    ),
    title3Small = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.023).em,
        fontFamily = PretendardSmall
    ),

    // Heading 1
    heading1Bold = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.0194).em,
        fontFamily = PretendardBold
    ),
    heading1Medium = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.0194).em,
        fontFamily = PretendardMedium
    ),
    heading1Small = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.0194).em,
        fontFamily = PretendardSmall
    ),

    // Heading 2
    heading2Bold = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.012).em,
        fontFamily = PretendardBold
    ),
    heading2Medium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.012).em,
        fontFamily = PretendardMedium
    ),
    heading2Small = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.012).em,
        fontFamily = PretendardSmall
    ),

    // Headline 1
    headline1Bold = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.002).em,
        fontFamily = PretendardBold
    ),
    headline1Medium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.002).em,
        fontFamily = PretendardMedium
    ),
    headline1Small = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.002).em,
        fontFamily = PretendardSmall
    ),

    // Headline 2
    headline2Bold = TextStyle(fontSize = 17.sp, lineHeight = 24.sp, fontFamily = PretendardBold),
    headline2Medium = TextStyle(
        fontSize = 17.sp,
        lineHeight = 24.sp,
        fontFamily = PretendardMedium
    ),
    headline2Small = TextStyle(fontSize = 17.sp, lineHeight = 24.sp, fontFamily = PretendardSmall),

    // Body 1
    body1Bold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.0057.em,
        fontFamily = PretendardBold
    ),
    body1Medium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.0057.em,
        fontFamily = PretendardMedium
    ),
    body1Small = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.0057.em,
        fontFamily = PretendardSmall
    ),

    // Body 2
    body2Bold = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.0096.em,
        fontFamily = PretendardBold
    ),
    body2Medium = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.0096.em,
        fontFamily = PretendardMedium
    ),
    body2Small = TextStyle(
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.0096.em,
        fontFamily = PretendardSmall
    ),

    // Label 1
    label1Bold = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.0145.em,
        fontFamily = PretendardBold
    ),
    label1Medium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.0145.em,
        fontFamily = PretendardMedium
    ),
    label1Small = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.0145.em,
        fontFamily = PretendardSmall
    ),

    // Label 2
    label2Bold = TextStyle(
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.0194.em,
        fontFamily = PretendardBold
    ),
    label2Medium = TextStyle(
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.0194.em,
        fontFamily = PretendardMedium
    ),
    label2Small = TextStyle(
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.0194.em,
        fontFamily = PretendardSmall
    ),

    // Caption 1
    caption1Bold = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0252.em,
        fontFamily = PretendardBold
    ),
    caption1Medium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0252.em,
        fontFamily = PretendardMedium
    ),
    caption1Small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0252.em,
        fontFamily = PretendardSmall
    ),

    // Caption 2
    caption2Bold = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0311.em,
        fontFamily = PretendardBold
    ),
    caption2Medium = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0311.em,
        fontFamily = PretendardMedium
    ),
    caption2Small = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0311.em,
        fontFamily = PretendardSmall
    )
)

val LocalBbangZipTypography = staticCompositionLocalOf { defaultBbangZipTypography }