package org.android.bbangzip.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class ShadowConfig(
    val color: Color,
    val blur: Dp,
    val offsetX: Dp,
    val offsetY: Dp,
    val spread: Dp,
)
