package org.andsopt.android.bbangzip.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object BbangZipTheme {
    val colors: BbangZipColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBbangZipColors.current

    val typography: BbangZipTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalBbangZipTypography.current

    val opacity: BbangZipOpacity
        @Composable
        @ReadOnlyComposable
        get() = LocalBbangZipOpacity.current
}