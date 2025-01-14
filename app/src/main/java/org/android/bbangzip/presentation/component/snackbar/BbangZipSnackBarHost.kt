package org.android.bbangzip.presentation.component.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipSnackBarHost(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackBarHostState,
        snackbar = { data ->
            BbangZipSnackBar(
                text = data.visuals.message,
                textStyle = BbangZipTheme.typography.label2Bold,
                textColor = BbangZipTheme.colors.staticWhite_FFFFFF,
                containerColor = BbangZipTheme.colors.labelAlternative_282119_61,
            )
        },
    )
}
