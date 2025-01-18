package org.android.bbangzip.presentation.util.graphic

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Int.pixelsToDp() = LocalDensity.current.run { this@pixelsToDp.toDp() }

@Composable
fun ColumnScope.Gap(height: Int? = null) {
    if (height == null) Spacer(modifier = Modifier.weight(1f))
    else Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun RowScope.Gap(width: Int? = null) {
    if (width == null) Spacer(modifier = Modifier.weight(1f))
    else Spacer(modifier = Modifier.width(width.dp))
}

@Composable
fun LazyItemScope.Gap(
    width: Int = 0,
    height: Int = 0
) {
    if(width == 0) Spacer(modifier = Modifier.height(height.dp))
    else if(height == 0) Spacer(modifier = Modifier.width(width.dp))
}