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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.component.card.ToDoCard
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
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
    val backgroundHeight = (screenHeightDp * 0.32).toInt()


    val tabs = listOf("중간고사", "기말고사")
    val todoList = listOf(
        ToDoCardModel(
            subjectName = "경제통계학개론",
            examName = "중간고사",
            studyContents = "경제통계학",
            startPage = 36,
            finishPage = 60,
            deadline = "2025년 4월 25일",
            pieceId = "1",
            remainingDays = 1,
        ),
        ToDoCardModel(
            subjectName = "경제통계학개론",
            examName = "중간고사",
            studyContents = "경제통계학",
            startPage = 36,
            finishPage = 60,
            deadline = "2025년 4월 25일",
            pieceId = "1",
            remainingDays = 1,
        ),
        ToDoCardModel(
            subjectName = "경제통계학개론",
            examName = "중간고사",
            studyContents = "경제통계학",
            startPage = 36,
            finishPage = 60,
            deadline = "2025년 4월 25일",
            pieceId = "1",
            remainingDays = 1,
        ),
        ToDoCardModel(
            subjectName = "경제통계학개론",
            examName = "중간고사",
            studyContents = "경제통계학",
            startPage = 36,
            finishPage = 60,
            deadline = "2025년 4월 25일",
            pieceId = "1",
            remainingDays = 1,
        )
    )
    var selectedIndex by remember { mutableIntStateOf(0) }
    var isMenuOpen by remember { mutableStateOf(false) }
    var motivationMessage by remember { mutableStateOf("사장님의 각오 한마디를 작성해보세요") }
    var todoViewType by remember { mutableStateOf(PieceViewType.DEFAULT) }

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
            state = listState
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
                    TwoLineTextWithWordWrap(motivationMessage)

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
                when(todoViewType){
                    PieceViewType.EMPTY -> {
                        Spacer(modifier = Modifier.height(84.dp))
                        EmptySubjectCardView()
                    }
                    PieceViewType.DEFAULT -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(52.dp))

                            Row(
                                modifier = Modifier.padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "시험까지 D-14",
                                    style = BbangZipTheme.typography.caption1Medium,
                                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                                    modifier = Modifier
                                        .background(
                                            color = BbangZipTheme.colors.statusPositive_3D3730,
                                            shape = RoundedCornerShape(11.dp)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "2025년 11월 25일",
                                    style = BbangZipTheme.typography.caption1Medium,
                                    color = BbangZipTheme.colors.labelAlternative_282119_61
                                )
                            }

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "학습 내용",
                                    style = BbangZipTheme.typography.heading2Bold,
                                    color = BbangZipTheme.colors.labelAlternative_282119_61

                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash_default_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .applyFilterOnClick(
                                            radius = 20.dp,
                                            isDisabled = false
                                        )
                                        {  }
                                        .padding(8.dp)
                                )

                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus_default_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .applyFilterOnClick(
                                            radius = 20.dp,
                                            isDisabled = false
                                        )
                                        {  }
                                        .padding(8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            todoList.forEach { item ->
                                ToDoCard(
                                    state = BbangZipCardState.DEFAULT,
                                    data = item
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                    PieceViewType.DELETE -> {

                    }
                }
            }
        }
        Column(){
            BbangZipBaseTopBar(
                scrollState = listState,
                backGroundColor = BbangZipTheme.colors.backgroundAccent_FFDAA0,
                leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
                trailingIcon = R.drawable.ic_menu_kebab_default_24,
                onTrailingIconClick = { isMenuOpen = !isMenuOpen },
                title = "경제통계학"
            )
            if (isMenuOpen) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .height(128.dp)
                        .width(200.dp)
                        .applyShadows(BbangZipShadowType.HEAVY, shape = RoundedCornerShape(32.dp))
                        .align(Alignment.End)
                        .offset(y = (-8).dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = BbangZipTheme.colors.staticWhite_FFFFFF,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "각오 한 마디 작성하기",
                            style = BbangZipTheme.typography.body1Bold,
                            color = BbangZipTheme.colors.labelNormal_282119,
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyFilterOnClick(
                                    radius = 16.dp,
                                    isDisabled = false
                                ) { }
                                .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "과목명 수정하기",
                            style = BbangZipTheme.typography.body1Bold,
                            color = BbangZipTheme.colors.labelNormal_282119,
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyFilterOnClick(
                                    radius = 16.dp,
                                    isDisabled = false
                                ) { }
                                .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                        )
                    }
                }
            }
        }
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
                .background(color = if (isSelected) BbangZipTheme.colors.labelNormal_282119 else BbangZipTheme.colors.staticWhite_FFFFFF)
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