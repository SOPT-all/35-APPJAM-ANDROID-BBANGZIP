package org.android.bbangzip.presentation.util.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
fun applyPressedFilter(backgroundColor: Color): Color {
    val filterColor = Color(0xFF282119).copy(alpha = 0.12f)
    return filterColor.compositeOver(backgroundColor)
}