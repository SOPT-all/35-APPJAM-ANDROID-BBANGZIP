package org.android.bbangzip.presentation.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.ui.theme.BbangZipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbangZipTwoButtonBottomSheet(
    isBottomSheetVisible: Boolean,
    bottomSheetTitle: String,
    interactButtonText: String,
    cancelButtonText: String,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onClickInteractButton: () -> Unit = {},
    onClickCancelButton: () -> Unit = {},
) {
    BbangZipBasicModalBottomSheet(
        modifier = modifier,
        isBottomSheetVisible = isBottomSheetVisible,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = bottomSheetTitle,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 15.dp),
                style = BbangZipTheme.typography.headline1Bold,
                color = BbangZipTheme.colors.labelNeutral_282119_88,
            )
        },
        interactButton = {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClickInteractButton,
            ) {
                Text(
                    text = interactButtonText,
                    style = BbangZipTheme.typography.body1Bold,
                    color = BbangZipTheme.colors.staticWhite_FFFFFF,
                )
            }
            // TODO Button 집어넣기
        },
        cancelButton = {
            Spacer(modifier = Modifier.height(8.dp))

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
private fun BbangZipTwoButtonBottomSheetPreview() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(true) }

    BbangZipTwoButtonBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        bottomSheetTitle = "로그아웃 하시겠어요?",
        onDismissRequest = { isBottomSheetVisible = !isBottomSheetVisible },
        interactButtonText = "로그아웃 하기",
        cancelButtonText = "취소",
        onClickCancelButton = { isBottomSheetVisible = !isBottomSheetVisible },
    )
}
