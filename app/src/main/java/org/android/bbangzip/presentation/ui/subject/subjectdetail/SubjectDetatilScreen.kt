package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun SubjectDetailScreen(
    padding: PaddingValues,
) {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val bottomBarPadding = padding.calculateBottomPadding()
    val backgroundHeight = (screenHeightDp * 0.32).toInt()

    val tabs = listOf("중간고사", "기말고사")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(backgroundHeight.dp)
                        .background(
                            color = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                        )
                ){
                    TwoLineTextWithWordWrap("사장님의 각오 한마디를 작성해보세요")

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .offset(y = 32.dp)
                            .height(64.dp)
                            .background(
                                color = BbangZipTheme.colors.staticWhite_FFFFFF,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .align(Alignment.BottomCenter)
                            .applyShadows(
                                BbangZipShadowType.EMPHASIZE,
                                shape = RoundedCornerShape(32.dp)
                            )
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                                    shape = RoundedCornerShape(32.dp)
                                )
                                .padding(horizontal = 76.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            tabs.forEachIndexed { index, tabName ->
                                examTab(
                                    text = tabName,
                                    isSelected = selectedIndex == index,
                                    onClick = { selectedIndex = index } // 클릭 시 인덱스 변경
                                )
                            }
                        }
                    }
                }
            }
            item{
                Spacer(modifier = Modifier.height(80.dp))
                EmptySubjectCardView()
            }
        }
        BbangZipBaseTopBar(
            backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            trailingIcon = R.drawable.ic_menu_kebab_default_24,
            title = "경제통계학"
        )
    }
}

@Composable
private fun examTab(
    text : String,
    isSelected : Boolean,
    onClick : () -> Unit
) {
    Column(
        modifier = Modifier.noRippleClickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = BbangZipTheme.typography.body1Bold,
            color = if(isSelected) BbangZipTheme.colors.labelNormal_282119 else BbangZipTheme.colors.labelAssistive_282119_28
        )

        Spacer(Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .height(2.dp)
                .width(40.dp)
                .background(color = if(isSelected) BbangZipTheme.colors.labelNormal_282119 else BbangZipTheme.colors.staticWhite_FFFFFF)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SubjectDetailScreenPreview() {
    SubjectDetailScreen(
        PaddingValues(64.dp)
    )
}

@Composable
fun TwoLineTextWithWordWrap(
    text: String,
) {
    var displayText by remember(text) { mutableStateOf<AnnotatedString?>(null) }

    BasicText(
        text = displayText ?: AnnotatedString(text),
        modifier = Modifier
            .width(200.dp)
            .padding(top = 92.dp, start = 20.dp)
            .height(56.dp)
        ,
        style = BbangZipTheme.typography.heading2Bold,
        onTextLayout = { textLayoutResult ->
            if (displayText == null) { // 텍스트 변경 시 재계산
                displayText = processTextForWordWrap(text, textLayoutResult, 2)
            }
        },
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = { defaultBbangZipColors.labelAlternative_282119_61 }
    )
}

private fun processTextForWordWrap(
    text: String,
    textLayoutResult: TextLayoutResult,
    maxLines: Int
): AnnotatedString {
    val lines = textLayoutResult.lineCount.coerceAtMost(maxLines)
    if (lines <= 1) return AnnotatedString(text)

    val builder = AnnotatedString.Builder()
    var startIndex = 0

    for (lineIndex in 0 until lines) {
        val endIndex = textLayoutResult.getLineEnd(lineIndex, visibleEnd = false).coerceAtMost(text.length)
        val lineText = text.substring(startIndex, endIndex)

        if (lineIndex == lines - 1 && endIndex < text.length) {
            val lastSpaceIndex = lineText.lastIndexOf(' ')
            if (lastSpaceIndex != -1) {
                builder.append(lineText.substring(0, lastSpaceIndex))
                builder.append("\n")
                builder.append(text.substring(startIndex + lastSpaceIndex + 1))
                break
            }
        }

        builder.append(lineText)
        if (lineIndex < lines - 1) builder.append("\n")
        startIndex = endIndex
    }

    return builder.toAnnotatedString()
}

@Composable
private fun EmptySubjectCardView(
    modifier: Modifier = Modifier,
    onAddSubjectButtonClicked: () -> Unit = {},
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(328.dp)
                .background(color = BbangZipTheme.colors.backgroundAlternative_F5F5F5, shape = RoundedCornerShape(size = 32.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.empty_view_text),
                )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BbangZipButton(
            bbangZipButtonType = BbangZipButtonType.Solid,
            bbangZipButtonSize = BbangZipButtonSize.Large,
            onClick = { onAddSubjectButtonClicked() },
            label = stringResource(R.string.btn_add_todo_label),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = R.drawable.ic_plus_thick_24,
        )
    }
}