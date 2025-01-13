package org.android.bbangzip.presentation.type

import androidx.compose.ui.graphics.Color
import org.android.bbangzip.ui.theme.defaultBbangZipColors

enum class PushIconType(
    val backgroundColor: Color,
    val textColor: Color,
) {
    POSITIVE(
        backgroundColor = defaultBbangZipColors.statusPositive_3D3730,
        textColor = defaultBbangZipColors.staticWhite_FFFFFF,
    ),
    DISABLE(
        backgroundColor = defaultBbangZipColors.fillAlternative_68645E_05,
        textColor = defaultBbangZipColors.labelDisable_282119_12,
    ),
}
