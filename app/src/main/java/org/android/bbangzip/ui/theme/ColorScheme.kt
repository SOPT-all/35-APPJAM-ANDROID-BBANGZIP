package org.android.bbangzip.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class BbangZipColors(
    // Primary
    val primaryLight: Color,
    val primaryNormal: Color,
    val primaryHeavy: Color,
    // Secondary
    val secondaryLight: Color,
    val secondaryNormal: Color,
    val secondaryHeavy: Color,
    // Label
    val labelStrong: Color,
    val labelNormal: Color,
    val labelNeutral: Color,
    val labelAlternative: Color,
    val labelAssistive: Color,
    val labelDisable: Color,
    // Background
    val backgroundNormal: Color,
    val backgroundAlternative: Color,
    val backgroundAccent: Color,
    // Interaction
    val interactionInactive: Color,
    val interactionDisable: Color,
    // Line
    val lineNormal: Color,
    val lineNeutral: Color,
    val lineAlternative: Color,
    val lineStrong: Color,
    // Status
    val statusPositive: Color,
    val statusCautionary: Color,
    val statusDestructive: Color,
    // Static
    val staticWhite: Color,
    val staticBlack: Color,
    // Component Fill
    val fillNormal: Color,
    val fillStrong: Color,
    val fillAlternative: Color,
    // Material
    val materialDimmer: Color,
    // Social Kakao
    val kakaoContainer: Color,
    val kakaoSymbol: Color,
    val kakaoLabel: Color,
)

val defaultBbangZipColors =
    BbangZipColors(
        // Primary
        primaryLight = PrimaryLight,
        primaryNormal = PrimaryNormal,
        primaryHeavy = PrimaryHeavy,
        // Secondary
        secondaryLight = SecondaryLight,
        secondaryNormal = SecondaryNormal,
        secondaryHeavy = SecondaryHeavy,
        // Label
        labelStrong = LabelStrong,
        labelNormal = LabelNormal,
        labelNeutral = LabelNeutral,
        labelAlternative = LabelAlternative,
        labelAssistive = LabelAssistive,
        labelDisable = LabelDisable,
        // Background
        backgroundNormal = BackgroundNormal,
        backgroundAlternative = BackgroundAlternative,
        backgroundAccent = BackgroundAccent,
        // Interaction
        interactionInactive = InteractionInactive,
        interactionDisable = InteractionDisable,
        // Line
        lineNormal = LineNormal,
        lineNeutral = LineNeutral,
        lineAlternative = LineAlternative,
        lineStrong = LineStrong,
        // Status
        statusPositive = StatusPositive,
        statusCautionary = StatusCautionary,
        statusDestructive = StatusDestructive,
        // Static
        staticWhite = StaticWhite,
        staticBlack = StaticBlack,
        // Component Fill
        fillNormal = FillNormal,
        fillStrong = FillStrong,
        fillAlternative = FillAlternative,
        // Material
        materialDimmer = MaterialDimmer,
        // Social Kakao
        kakaoContainer = KakaoContainer,
        kakaoSymbol = KakaoSymbol,
        kakaoLabel = KakaoLabel,
    )

val LocalBbangZipColors = staticCompositionLocalOf { defaultBbangZipColors }
