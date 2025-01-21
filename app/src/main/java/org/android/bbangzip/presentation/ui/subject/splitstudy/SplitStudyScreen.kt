package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.chip.BbangZipChip
import org.android.bbangzip.presentation.component.textfield.BbangZipSimpleTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.type.BbangZipShadowType
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.presentation.util.modifier.applyShadows
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

@Composable
fun SplitStudyScreen(
    pieceNumber: Int = 0,
    subjectName: String = "",
    startPage: String = "",
    endPage: String = "",
    startPageList: List<String> = emptyList(),
    endPageList: List<String> = emptyList(),
    seletedDate: List<Date> = listOf(
        Date("2025", "1", "21"),
        Date("2025", "1", "21"),
        Date("2025", "1", "21"),
        Date("2025", "1", "21"),
        Date("2025", "1", "21"),
        Date("2025", "1", "21")
    ),
    onChangeStartPage: (String) -> Unit = {},
    onChangeEndPage: (String) -> Unit = {},
    onChangeStartPageFocused: (Boolean) -> Unit = {},
    onChangeEndPageFocused: (Boolean) -> Unit = {},
    onBackBtnClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Timber.d("pieceNumber : $pieceNumber")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(color = BbangZipTheme.colors.backgroundNormal_FFFFFF)
    ) {
        BbangZipBaseTopBar(
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24,
            title = subjectName,
            onLeadingIconClick = { onBackBtnClick() },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .padding(horizontal = 16.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .applyShadows(
                            shadowType = BbangZipShadowType.EMPHASIZE,
                            shape = RoundedCornerShape(24.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = BbangZipTheme.colors.backgroundAlternative_F5F5F5,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp)
                    ){
                        Column(){
                            Text(
                                text = "학습 내용",
                                style = BbangZipTheme.typography.headline2Bold,
                                color = BbangZipTheme.colors.labelNormal_282119
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                BbangZipChip(
                                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                                    text = startPage,
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "까지",
                                    style = BbangZipTheme.typography.label1Bold,
                                    color = BbangZipTheme.colors.labelAlternative_282119_61
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                BbangZipChip(
                                    backgroundColor = BbangZipTheme.colors.statusPositive_3D3730,
                                    text = endPage,
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "까지",
                                    style = BbangZipTheme.typography.label1Bold,
                                    color = BbangZipTheme.colors.labelAlternative_282119_61
                                )
                            }
                        }
                    }
                }
            }

            item{
                Spacer(modifier = Modifier.height(32.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            items(
                count =  pieceNumber
            ){ index ->

                Column {
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "${index + 1}조각",
                        style = BbangZipTheme.typography.body1Bold,
                        color = BbangZipTheme.colors.labelNormal_282119
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BbangZipSimpleTextField(
                            leadingIcon = R.drawable.ic_page_check_default_24,
                            placeholder = R.string.add_study_start_page_placeholder,
                            guideline = R.string.add_study_start_page_guideline,
                            value = startPageList[index],
                            modifier = Modifier.weight(1f),
                            onValueChange = {
                                onChangeStartPage(it)
                            },
                            onFocusChange = { onChangeStartPageFocused(it) },
                            focusManager = focusManager,
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        BbangZipSimpleTextField(
                            leadingIcon = R.drawable.ic_page_check_default_24,
                            placeholder = R.string.add_study_end_page_placeholder,
                            guideline = R.string.add_study_end_page_guideline,
                            value = endPageList[index],
                            modifier = Modifier.weight(1f),
                            onValueChange = {
                                onChangeEndPage(it)
                            },
                            onFocusChange = { onChangeEndPageFocused(it) },
                            focusManager = focusManager
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    BbangZipButton(
                        bbangZipButtonType = BbangZipButtonType.Outlined,
                        bbangZipButtonSize = BbangZipButtonSize.Medium,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = R.drawable.ic_page_check_default_24,
                        label = "${seletedDate[index+1].year}년 ${seletedDate[index+1].month}월 ${seletedDate[index+1].day}일 까지",
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(88.dp)) }
        }

        BbangZipButton(
            bbangZipButtonSize = BbangZipButtonSize.Large,
            bbangZipButtonType = BbangZipButtonType.Solid,
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
            ,
            label = stringResource(R.string.btn_save_label),
            trailingIcon = R.drawable.ic_plus_thick_24
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplitStudyScreenPreview() {
    SplitStudyScreen(
    )
}

