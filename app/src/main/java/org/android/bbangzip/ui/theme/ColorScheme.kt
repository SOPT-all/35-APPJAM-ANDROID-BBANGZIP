package org.android.bbangzip.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class BbangZipColors(
    // Primary
    val primaryLight_3D3730: Color,
    val primaryNormal_282119: Color,
    val primaryStrong_1B1611: Color,
    // Secondary
    val secondaryNormal_FFCD80: Color,
    val secondaryStrong_FFC161: Color,
    val secondaryHeavy_FFB84A: Color,
    // Label
    val labelStrong_000000: Color,
    val labelNormal_282119: Color,
    val labelNeutral_282119_88: Color,
    val labelAlternative_282119_61: Color,
    val labelAssistive_282119_28: Color,
    val labelDisable_282119_12: Color,
    // Background
    val backgroundNormal_FFFFFF: Color,
    val backgroundAlternative_F5F5F5: Color,
    val backgroundAccent_FFDAA0: Color,
    // Interaction
    val interactionInactive_D4D3D1: Color,
    val interactionDisable_F5F5F5: Color,
    // Line
    val lineNormal_68645E_22: Color,
    val lineNeutral_68645E_16: Color,
    val lineAlternative_68645E_08: Color,
    val lineStrong_68645E_52: Color,
    // Status
    val statusPositive_3D3730: Color,
    val statusCautionary_FFB84A: Color,
    val statusDestructive_FF8345: Color,
    // Static
    val staticWhite_FFFFFF: Color,
    val staticBlack_000000: Color,
    // Component Fill
    val fillNormal_68645E_08: Color,
    val fillStrong_68645E_16: Color,
    val fillAlternative_68645E_05: Color,
    // Material
    val materialDimmer_282119_52: Color,
    // Social Kakao
    val kakaoContainer_FEE500: Color,
    val kakaoSymbol_000000: Color,
    val kakaoLabel_000000_85: Color,
    // Splash
    val splashBackground_FFF9EF: Color,
    val splashLogo_886759: Color,
    // My
    val myBbangZip_9E6B45: Color,
)

val defaultBbangZipColors =
    BbangZipColors(
        // Primary
        primaryLight_3D3730 = Neutral40,
        primaryNormal_282119 = Neutral30,
        primaryStrong_1B1611 = Neutral15,
        // Secondary
        secondaryNormal_FFCD80 = Yellow70,
        secondaryStrong_FFC161 = Yellow60,
        secondaryHeavy_FFB84A = Yellow50,
        // Label
        labelStrong_000000 = Common0,
        labelNormal_282119 = Neutral30,
        labelNeutral_282119_88 = Neutral30.copy(alpha = 0.88f),
        labelAlternative_282119_61 = Neutral30.copy(alpha = 0.61f),
        labelAssistive_282119_28 = Neutral30.copy(alpha = 0.28f),
        labelDisable_282119_12 = Neutral30.copy(alpha = 0.12f),
        // Background
        backgroundNormal_FFFFFF = Common100,
        backgroundAlternative_F5F5F5 = Neutral99,
        backgroundAccent_FFDAA0 = Yellow80,
        // Interaction
        interactionInactive_D4D3D1 = Neutral90,
        interactionDisable_F5F5F5 = Neutral99,
        // Line
        lineNormal_68645E_22 = Neutral60.copy(alpha = 0.22f),
        lineNeutral_68645E_16 = Neutral60.copy(alpha = 0.16f),
        lineAlternative_68645E_08 = Neutral60.copy(alpha = 0.08f),
        lineStrong_68645E_52 = Neutral60.copy(alpha = 0.52f),
        // Status
        statusPositive_3D3730 = Neutral40,
        statusCautionary_FFB84A = Yellow50,
        statusDestructive_FF8345 = Orange60,
        // Static
        staticWhite_FFFFFF = Common100,
        staticBlack_000000 = Common0,
        // Component Fill
        fillNormal_68645E_08 = Neutral60.copy(alpha = 0.08f),
        fillStrong_68645E_16 = Neutral60.copy(alpha = 0.16f),
        fillAlternative_68645E_05 = Neutral60.copy(alpha = 0.05f),
        // Material
        materialDimmer_282119_52 = Neutral30.copy(alpha = 0.52f),
        // Social Kakao
        kakaoContainer_FEE500 = Yellow55,
        kakaoSymbol_000000 = Common0,
        kakaoLabel_000000_85 = Common0.copy(alpha = 0.85f),
        // Splash
        splashBackground_FFF9EF = Yellow99,
        splashLogo_886759 = Brown10,
        // My
        myBbangZip_9E6B45 = Brown20,
    )

val LocalBbangZipColors = staticCompositionLocalOf { defaultBbangZipColors }
