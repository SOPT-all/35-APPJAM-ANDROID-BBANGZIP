package org.android.bbangzip.presentation.type

import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import org.android.bbangzip.ui.theme.defaultBbangZipOpacity
import org.android.bbangzip.ui.theme.model.ShadowOption

enum class BbangZipShadowType(
    val shadowOptions: List<ShadowOption>,
) {
    EMPTY(
        listOf(),
    ),
    Emphasize(
        listOf(
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity12),
                blur = 8.dp,
                offsetX = 0.dp,
                offsetY = 2.dp,
                spread = 0.dp,
            ),
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity8),
                blur = 4.dp,
                offsetX = 0.dp,
                offsetY = 1.dp,
                spread = 0.dp,
            ),
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity8),
                blur = 1.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp,
            ),
        ),
    ),
    Strong(
        listOf(
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity12),
                blur = 12.dp,
                offsetX = 0.dp,
                offsetY = 6.dp,
                spread = 0.dp,
            ),
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity12),
                blur = 8.dp,
                offsetX = 0.dp,
                offsetY = 4.dp,
                spread = 0.dp,
            ),
            ShadowOption(
                color = defaultBbangZipColors.staticBlack_000000.copy(defaultBbangZipOpacity.opacity8),
                blur = 4.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                spread = 0.dp,
            ),
        ),
    ),
}
