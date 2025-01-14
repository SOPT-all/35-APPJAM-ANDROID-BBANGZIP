package org.android.bbangzip.presentation.component.card

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.type.BbangZipCardType
import org.android.bbangzip.presentation.type.BbangZipShadowType

@Parcelize
sealed class BbangZipCardState : Parcelable {
    data object DEFAULT : BbangZipCardState()

    data object CHECKABLE : BbangZipCardState()

    data object CHECKED : BbangZipCardState()

    data object COMPLETE : BbangZipCardState()
}

private val stateToTypeMap =
    mapOf(
        BbangZipCardState.DEFAULT to BbangZipCardType.DEFAULT,
        BbangZipCardState.CHECKABLE to BbangZipCardType.CHECKABLE,
        BbangZipCardState.CHECKED to BbangZipCardType.CHECKED,
        BbangZipCardState.COMPLETE to BbangZipCardType.COMPLETE,
    )

fun BbangZipCardState.getBackgroundColor(): Color = stateToTypeMap[this]?.backgroundColor ?: Color.Transparent

fun BbangZipCardState.getBorderColor(): Color = stateToTypeMap[this]?.borderColor ?: Color.Transparent

fun BbangZipCardState.getBorderWidth(): Dp = stateToTypeMap[this]?.borderWidth ?: 0.dp

fun BbangZipCardState.getRadius(): Dp = stateToTypeMap[this]?.radius ?: 0.dp

fun BbangZipCardState.getShadowOptions(): BbangZipShadowType = stateToTypeMap[this]?.shadowOptions ?: BbangZipShadowType.EMPTY
