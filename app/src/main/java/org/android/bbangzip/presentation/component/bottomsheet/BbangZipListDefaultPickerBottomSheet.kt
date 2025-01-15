package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun BbangZipListDefaultPickerBottomSheet(
    isBottomSheetVisible: Boolean,
    itemList: List<String>,
    modifier: Modifier = Modifier,
    title: @Composable (ColumnScope.() -> Unit) = {},
    selectedItem: Int = 0,
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
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
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
                                // TODO focused 확장 clickable로 변경,
                                .clickable { onSelectedItemChanged(index) }
                                .background(
                                    color = if (index != selectedItem) BbangZipTheme.colors.staticWhite_FFFFFF else BbangZipTheme.colors.fillStrong_68645E_16,
                                    shape = RoundedCornerShape(16.dp),
                                )
                                .padding(vertical = 8.dp),
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
private fun BbangZipListDefaultPickerBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }
    var selectedSort by rememberSaveable { mutableIntStateOf(0) }

    BBANGZIPTheme {
        BbangZipListDefaultPickerBottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
            itemList =
                listOf(
                    "최근 등록 순",
                    "분량 적은 순",
                    "마감 기한 빠른 순",
                ),
            selectedItem = 1,
            onSelectedItemChanged = { index ->
                selectedSort = index
                isBottomSheetVisible = !isBottomSheetVisible
            },
        )
    }
}
