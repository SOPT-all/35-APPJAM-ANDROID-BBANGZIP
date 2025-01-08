package org.android.bbangzip.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun BBANGZIPTheme(
    content: @Composable () -> Unit
) {
    val bbangZipColors = defaultBbangZipColors
    val bbangZipTypography = defaultBbangZipTypography
    val bbangZipOpacity = defaultBbangZipOpacity
    MaterialTheme(
        content = {
            CompositionLocalProvider(
                LocalBbangZipColors provides bbangZipColors,
                LocalBbangZipTypography provides bbangZipTypography,
                LocalBbangZipOpacity provides bbangZipOpacity
            ) {
                content()
            }
        }
    )
}