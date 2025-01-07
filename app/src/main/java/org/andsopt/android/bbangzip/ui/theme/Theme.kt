package org.andsopt.android.bbangzip.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun BBANGZIPTheme(
    content: @Composable () -> Unit
) {
    val bbangZipColors = defaultBbangZipColors
    val bbangZipTypography = defaultBbangZipTypography

    MaterialTheme(
        content = {
            CompositionLocalProvider(
                LocalBbangZipColors provides bbangZipColors,
                LocalBbangZipTypography provides bbangZipTypography
            ) {
                content()
            }
        }
    )
}