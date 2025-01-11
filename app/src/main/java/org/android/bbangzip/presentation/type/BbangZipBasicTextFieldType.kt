package org.android.bbangzip.presentation.type

import androidx.compose.ui.graphics.Color
import org.android.bbangzip.ui.theme.defaultBbangZipColors

enum class BbangZipBasicTextFieldType(
    val iconColor: Color = defaultBbangZipColors.labelAssistive_282119_28,
    val textColor: Color = defaultBbangZipColors.labelNormal_282119,
    val characterCheckColor: Color = defaultBbangZipColors.labelAlternative_282119_61,
    val borderColor: Color = Color.Transparent,
    val backgroundColor: Color = defaultBbangZipColors.fillNormal_68645E_08,
    val guidelineColor: Color = defaultBbangZipColors.labelAssistive_282119_28
) {
    EMPTY(
        textColor = defaultBbangZipColors.labelAssistive_282119_28,
        characterCheckColor = defaultBbangZipColors.labelDisable_282119_12
    ),
    TYPING(
        backgroundColor = defaultBbangZipColors.fillStrong_68645E_16
    ),
    SUCCESS,
    ERROR(
        borderColor = defaultBbangZipColors.statusDestructive_FF8345,
        guidelineColor = defaultBbangZipColors.statusDestructive_FF8345
    )
}