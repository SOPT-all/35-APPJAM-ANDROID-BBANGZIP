package org.android.bbangzip.presentation.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.type.BbangZipTextFieldType

@Parcelize
sealed class BbangZipTextFieldInputState : Parcelable {
    data object Default : BbangZipTextFieldInputState()

    data object Placeholder : BbangZipTextFieldInputState()

    data object Typing : BbangZipTextFieldInputState()

    data object Field : BbangZipTextFieldInputState()

    data object Alert : BbangZipTextFieldInputState()
}

private val stateToTypeMap =
    mapOf(
        BbangZipTextFieldInputState.Default to BbangZipTextFieldType.DEFAULT,
        BbangZipTextFieldInputState.Placeholder to BbangZipTextFieldType.PLACEHOLDER,
        BbangZipTextFieldInputState.Typing to BbangZipTextFieldType.TYPING,
        BbangZipTextFieldInputState.Field to BbangZipTextFieldType.FIELD,
        BbangZipTextFieldInputState.Alert to BbangZipTextFieldType.ALERT,
    )

fun BbangZipTextFieldInputState.getIconColor(): Color {
    return stateToTypeMap[this]?.iconColor ?: Color.Transparent
}

fun BbangZipTextFieldInputState.getTextColor(): Color {
    return stateToTypeMap[this]?.textColor ?: Color.Transparent
}

fun BbangZipTextFieldInputState.getCharacterCheckColor(): Color {
    return stateToTypeMap[this]?.characterCheckColor ?: Color.Transparent
}

fun BbangZipTextFieldInputState.getBorderColor(): Color {
    return stateToTypeMap[this]?.borderColor ?: Color.Transparent
}

fun BbangZipTextFieldInputState.getBackgroundColor(): Color {
    return stateToTypeMap[this]?.backgroundColor ?: Color.Transparent
}

fun BbangZipTextFieldInputState.getGuidelineColor(): Color {
    return stateToTypeMap[this]?.guidelineColor ?: Color.Transparent
}
