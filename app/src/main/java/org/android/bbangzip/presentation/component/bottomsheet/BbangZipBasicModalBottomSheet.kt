package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipBasicModalBottomSheet(
    isBottomSheetVisible: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    title: @Composable (ColumnScope.() -> Unit) = {},
    content: @Composable (ColumnScope.() -> Unit) = {},
    interactButton: @Composable (ColumnScope.() -> Unit) = {},
    cancelButton: @Composable (ColumnScope.() -> Unit) = {},
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle(color = BbangZipTheme.colors.interactionInactive_D4D3D1) },
            shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
            containerColor = BbangZipTheme.colors.backgroundNormal_FFFFFF,
            scrimColor = BbangZipTheme.colors.materialDimmer_282119_52,
        ) {
            Column(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                title()
                content()
                interactButton()
                cancelButton()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BasicModalBottomSheetPreview() {
    BBANGZIPTheme {
        var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }

        BbangZipBasicModalBottomSheet(
            modifier = Modifier.padding(horizontal = 14.dp),
            isBottomSheetVisible = isBottomSheetVisible,
            onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
            title = {
                Text(
                    text = "BbangZip Basic Modal Bottom Sheet Title",
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 15.dp),
                )
            },
            content = {
                Text(text = "BbangZip Basic Modal Bottom Sheet Content")
            },
        )
    }
}
