package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipGetBadgeBottomSheet(
    isBottomSheetVisible: Boolean,
    cancelButtonText: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onClickCancelButton: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.badge_get_bottom_sheet_title),
                    style = BbangZipTheme.typography.heading2Bold,
                    color = BbangZipTheme.colors.labelNormal_282119,
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        },
        cancelButton = {
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onClickCancelButton,
            ) {
                Text(
                    text = cancelButtonText,
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.primaryNormal_282119,
                )
            }
            // TODO Button 집어넣기
        },
    )
}
