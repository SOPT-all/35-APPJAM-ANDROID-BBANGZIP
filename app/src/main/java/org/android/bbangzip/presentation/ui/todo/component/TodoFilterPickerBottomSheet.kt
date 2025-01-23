package org.android.bbangzip.presentation.ui.todo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.component.bottomsheet.BbangZipBasicModalBottomSheet
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoFilterPickerBottomSheet(
    isBottomSheetVisible: Boolean,
    modifier: Modifier = Modifier,
    title: @Composable (ColumnScope.() -> Unit) = {},
    selectedItem: ToDoFilterType = ToDoFilterType.RECENT,
    onSelectedItemChanged: (ToDoFilterType) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        title = { title() },
        content = {
            LazyColumn(
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                itemsIndexed(
                    ToDoFilterType.entries,
                    key = { _, item -> item },
                ) { _, item ->
                    Text(
                        text = item.filter,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .applyFilterOnClick { onSelectedItemChanged(item) }
                                .background(
                                    color = if (item != selectedItem) BbangZipTheme.colors.staticWhite_FFFFFF else BbangZipTheme.colors.fillStrong_68645E_16,
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
