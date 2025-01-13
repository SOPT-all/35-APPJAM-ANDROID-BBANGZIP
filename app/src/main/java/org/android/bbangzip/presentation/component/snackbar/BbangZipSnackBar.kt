package org.android.bbangzip.presentation.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun BbangZipSnackBar(
    text: String,
    textStyle: TextStyle,
    textColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(12.dp)
            .background(color = containerColor, shape = RoundedCornerShape(size = 16.dp))
            .padding(vertical = 7.dp , horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}