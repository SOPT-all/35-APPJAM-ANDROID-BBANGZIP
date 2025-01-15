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
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import timber.log.Timber

@Composable
fun Picker(
    items: List<String>,
    state: PickerState = rememberPickerState(),
    modifier: Modifier = Modifier,
    startIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier,
    isCircular: Boolean = true, // 무한 스크롤 여부
) {
    val pickerItems: List<String> = if (isCircular) items else List(visibleItemsCount / 2) { "" } + items + List(visibleItemsCount / 2) { "" }
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
    val listStartIndex =
        if (isCircular) {
            listScrollMiddle - listScrollMiddle % pickerItems.size - visibleItemsMiddle + startIndex
        } else {
            0 + startIndex // isCircular가 false이면 시작 인덱스를 0으로 설정
        }

    fun getItem(index: Int) = pickerItems[index % pickerItems.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)

    Timber.tag("zz").d(listState.firstVisibleItemIndex.toString())
    // 사용자가 스크롤을 멈출 때 애매하게 멈추지 않도록 하는 역할
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.intValue)

    val fadingEdgeGradient =
        remember {
            Brush.verticalGradient(
                0f to Color.Transparent,
                0.5f to Color.Black,
                1f to Color.Transparent,
            )
        }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex } // 얘가 변경되면
            .map { index ->
                getItem(index + visibleItemsMiddle)
            } // item 방출
            .distinctUntilChanged() // 실제 변경될 때만 flow가 값을 방출
            .collect { item -> state.selectedItem = item } //  수집 받으면 selectedItem 변경
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
                    .background(color = defaultBbangZipColors.fillAlternative_68645E_05),
        )
    }
}

private fun Modifier.fadingEdge(brush: Brush) =
    this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

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
            val valuesPickerState = rememberPickerState()
            val units = remember { listOf("seconds", "minutes", "hours") }
            val unitsPickerState = rememberPickerState()

            Text(text = "Example Picker", modifier = Modifier.padding(top = 16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Picker(
                    state = valuesPickerState,
                    items = values,
                    visibleItemsCount = 5,
                    modifier = Modifier.weight(0.3f),
                    textModifier = Modifier.padding(8.dp),
                    isCircular = false,
                )

                Picker(
                    state = unitsPickerState,
                    items = units,
                    visibleItemsCount = 5,
                    modifier = Modifier.weight(0.7f),
                    textModifier = Modifier.padding(8.dp),
                    isCircular = true,
                )
            }

            Text(
                text = "Interval: ${valuesPickerState.selectedItem} ${unitsPickerState.selectedItem}",
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }
    }
}
