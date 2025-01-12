package org.android.bbangzip.presentation.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BbangZipBasicTextFieldSlot(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (RowScope.() -> Unit) = {},
    content: @Composable (RowScope.() -> Unit) = {},
    characterCount: @Composable (RowScope.() -> Unit) = {},
    trailingIcon: @Composable (RowScope.() -> Unit) = {},
    guideline: @Composable (ColumnScope.() -> Unit) = {}
) {
    Column {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            leadingIcon()
            content()
            characterCount()
            trailingIcon()
        }
        guideline()
    }
}