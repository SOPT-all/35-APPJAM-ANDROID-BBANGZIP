package org.android.bbangzip.presentation.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.type.PushIconType

@Parcelize
sealed class BbangZipPushIconState : Parcelable {
    data object Positive : BbangZipPushIconState()

    data object Disable : BbangZipPushIconState()
}

private val stateToTypeMap =
    mapOf(
        BbangZipPushIconState.Positive to PushIconType.POSITIVE,
        BbangZipPushIconState.Disable to PushIconType.DISABLE,
    )

fun BbangZipPushIconState.getBackgroundColor(): Color {
    return stateToTypeMap[this]?.backgroundColor ?: Color.Transparent
}

fun BbangZipPushIconState.getTextColor(): Color {
    return stateToTypeMap[this]?.textColor ?: Color.Transparent
}
