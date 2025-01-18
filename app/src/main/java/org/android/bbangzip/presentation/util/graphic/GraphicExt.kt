package org.android.bbangzip.presentation.util.graphic

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Int.pixelsToDp() = LocalDensity.current.run { this@pixelsToDp.toDp() }
