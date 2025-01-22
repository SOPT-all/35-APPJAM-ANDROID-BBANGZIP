package org.android.bbangzip.presentation.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipLoadingIndicator(
    modifier : Modifier = Modifier
){
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
    }
}