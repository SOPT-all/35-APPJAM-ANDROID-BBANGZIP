package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipListPickerBottomSheet(
    isBottomSheetVisible: Boolean,
    itemList: List<String>,
    modifier: Modifier = Modifier,
    title: @Composable (ColumnScope.() -> Unit) = {},
    onSelectedItemChanged: (Int) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        sheetState =
            rememberModalBottomSheetState(
                confirmValueChange = { newState ->
                    !(newState == SheetValue.Hidden && isBottomSheetVisible)
                },
            ),
        title = { title() },
        content = {
            LazyColumn(
                modifier = Modifier.padding(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                itemsIndexed(
                    itemList,
                    key = { _, item -> item },
                ) { index, item ->
                    Text(
                        text = itemList[index],
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                // TODO focused 확장 clickable로 변경
                                .clickable { onSelectedItemChanged(index) },
                        textAlign = TextAlign.Center,
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BbangZipListPickerBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }
    var selectedPiece by rememberSaveable { mutableStateOf<Int?>(null) }

    BBANGZIPTheme {
        BbangZipListPickerBottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            title = {
                Text(
                    text = "몇 조각으로 쪼개서 공부할까요?",
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 15.dp),
                    style = BbangZipTheme.typography.headline1Bold,
                    color = BbangZipTheme.colors.labelNeutral_282119_88,
                )
            },
            onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
            itemList =
                listOf(
                    "1조각",
                    "2조각",
                    "3조각",
                    "4조각",
                    "5조각",
                    "6조각",
                ),
            onSelectedItemChanged = { index ->
                selectedPiece = index
                isBottomSheetVisible = !isBottomSheetVisible
            },
        )
    }
}
