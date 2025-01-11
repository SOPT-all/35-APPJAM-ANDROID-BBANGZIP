package org.android.bbangzip.presentation.component.textfield

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.type.BbangZipBasicTextFieldType

@Parcelize
sealed class BbangZipBasicTextFieldInputState : Parcelable {
    data object Empty : BbangZipBasicTextFieldInputState()
    data object Typing : BbangZipBasicTextFieldInputState()
    data object Error : BbangZipBasicTextFieldInputState()
    data object Success : BbangZipBasicTextFieldInputState()
}

private val stateToTypeMap = mapOf(
    BbangZipBasicTextFieldInputState.Empty to BbangZipBasicTextFieldType.EMPTY,
    BbangZipBasicTextFieldInputState.Typing to BbangZipBasicTextFieldType.TYPING,
    BbangZipBasicTextFieldInputState.Error to BbangZipBasicTextFieldType.ERROR,
    BbangZipBasicTextFieldInputState.Success to BbangZipBasicTextFieldType.SUCCESS
)

fun BbangZipBasicTextFieldInputState.getIconColor(): Color {
    return stateToTypeMap[this]?.iconColor ?: Color.Transparent
}

fun BbangZipBasicTextFieldInputState.getTextColor(): Color {
    return stateToTypeMap[this]?.textColor ?: Color.Transparent
}

fun BbangZipBasicTextFieldInputState.getCharacterCheckColor(): Color {
    return stateToTypeMap[this]?.characterCheckColor ?: Color.Transparent
}

fun BbangZipBasicTextFieldInputState.getBorderColor(): Color {
    return stateToTypeMap[this]?.borderColor ?: Color.Transparent
}

fun BbangZipBasicTextFieldInputState.getBackgroundColor(): Color {
    return stateToTypeMap[this]?.backgroundColor ?: Color.Transparent
}

fun BbangZipBasicTextFieldInputState.getGuidelineColor(): Color {
    return stateToTypeMap[this]?.guidelineColor ?: Color.Transparent
}
