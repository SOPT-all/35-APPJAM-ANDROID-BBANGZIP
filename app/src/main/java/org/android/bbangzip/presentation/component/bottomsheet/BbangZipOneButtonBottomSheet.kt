package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipOneButtonBottomSheet(
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
            // TODO 승범 Badge Detail
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

@Preview(showBackground = true)
@Composable
private fun BbangZipOneButtonBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }

    BbangZipOneButtonBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
        cancelButtonText = "닫기",
        onClickCancelButton = { isBottomSheetVisible = !isBottomSheetVisible },
    )
}
