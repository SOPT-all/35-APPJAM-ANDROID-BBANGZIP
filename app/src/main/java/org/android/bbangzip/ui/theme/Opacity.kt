package org.andsopt.android.bbangzip.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf


@Immutable
data class BbangZipOpacity(
    val opacity0: Float,
    val opacity5: Float,
    val opacity8: Float,
    val opacity12: Float,
    val opacity16: Float,
    val opacity22: Float,
    val opacity28: Float,
    val opacity35: Float,
    val opacity43: Float,
    val opacity52: Float,
    val opacity61: Float,
    val opacity74: Float,
    val opacity88: Float,
    val opacity97: Float,
    val opacity100: Float,
)

val defaultBbangZipOpacity = BbangZipOpacity(
    opacity0 = 0f,
    opacity5 = 0.05f,
    opacity8 = 0.08f,
    opacity12 = 0.12f,
    opacity16 = 0.16f,
    opacity22 = 0.22f,
    opacity28 = 0.28f,
    opacity35 = 0.35f,
    opacity43 = 0.43f,
    opacity52 = 0.52f,
    opacity61 = 0.61f,
    opacity74 = 0.74f,
    opacity88 = 0.88f,
    opacity97 = 0.97f,
    opacity100 = 1f
)

val LocalBbangZipOpacity = staticCompositionLocalOf { defaultBbangZipOpacity }