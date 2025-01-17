package org.android.bbangzip.presentation.component.wheelpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.android.bbangzip.presentation.util.graphic.pixelsToDp
import org.android.bbangzip.presentation.util.modifier.fadingEdge
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

// isCircular : 무한 스크롤 여부
@Composable
fun BbangZipPicker(
    items: List<String>,
    modifier: Modifier = Modifier,
    onItemChanged: (String) -> Unit = {},
    startIndex: Int = 0,
    visibleItemsCount: Int = 5,
    textModifier: Modifier = Modifier,
    isCircular: Boolean = false,
) {
    val pickerItems: List<String> =
        if (isCircular) {
            items
        } else {
            List(visibleItemsCount / 2) { "" } + items + List(visibleItemsCount / 2) { "" }
        }
    // 보여지는 아이템의 가운데값 인덱스
    val visibleItemsMiddle = visibleItemsCount / 2
    // 무한 스크롤
    val listScrollCount =
        if (isCircular) {
            Integer.MAX_VALUE
        } else {
            if (visibleItemsCount > pickerItems.size) visibleItemsCount else pickerItems.size
        }
    // 무한 스크롤 상 가운데
    val listScrollMiddle by remember(items) {
        derivedStateOf { items.size + 1 }
    }
    // 스크롤 상 시작 인덱스
    // isCircular가 false이면 시작 인덱스를 0으로 설정
    val listStartIndex =
        if (isCircular) {
            listScrollMiddle - listScrollMiddle % pickerItems.size - visibleItemsMiddle + startIndex
        } else {
            0 + startIndex
        }

    fun getItem(index: Int) = pickerItems[index % pickerItems.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)

    // 사용자가 스크롤을 멈출 때 애매하게 멈추지 않도록 하는 역할
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = itemHeightPixels.intValue.pixelsToDp()

    val fadingEdgeGradient =
        remember {
            Brush.verticalGradient(
                0f to Color.Transparent,
                0.5f to Color.Black,
                1f to Color.Transparent,
            )
        }

    // 얘가 변경되면
    // item 방출
    // 실제 변경될 때만 flow가 값을 방출
    //  수집 받으면 selectedItem 변경
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index ->
                getItem(index + visibleItemsMiddle)
            }
            .distinctUntilChanged()
            .collect { item -> onItemChanged(item) }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(itemHeightDp * visibleItemsCount)
                    .fadingEdge(fadingEdgeGradient),
        ) {
            items(listScrollCount) { index ->
                Text(
                    text = getItem(index),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = BbangZipTheme.typography.heading2Bold,
                    modifier =
                        Modifier
                            .onSizeChanged { size -> itemHeightPixels.intValue = size.height }
                            .padding(8.dp)
                            .then(textModifier),
                )
            }
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .offset(y = itemHeightDp * visibleItemsMiddle)
                    .height(height = itemHeightDp)
                    .background(color = BbangZipTheme.colors.fillAlternative_68645E_05),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PickerPreview() {
    BBANGZIPTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            val values = remember { (1..99).map { it.toString() } }
            var valuesPickerState by remember { mutableStateOf("") }

            val units = remember { listOf("seconds", "minutes", "hours") }
            var unitsPickerState by remember { mutableStateOf("") }

            Text(text = "Example Picker", modifier = Modifier.padding(top = 16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                BbangZipPicker(
                    items = values,
                    modifier = Modifier.weight(0.3f),
                    onItemChanged = { valuesPickerState = it },
                    visibleItemsCount = 5,
                )

                BbangZipPicker(
                    items = units,
                    modifier = Modifier.weight(0.7f),
                    onItemChanged = { unitsPickerState = it },
                    visibleItemsCount = 5,
                    isCircular = true,
                )
            }

            Text(
                text = "Interval: $valuesPickerState $unitsPickerState",
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }
    }
}
