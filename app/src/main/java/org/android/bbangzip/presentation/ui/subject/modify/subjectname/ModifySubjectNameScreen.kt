package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.button.BbangZipButton
import org.android.bbangzip.presentation.component.textfield.BbangZipBasicTextField
import org.android.bbangzip.presentation.component.topbar.BbangZipBaseTopBar
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun ModifySubjectNameScreen(
    subjectName: String = "",
    isButtonEnable: Boolean = false,
    subjectId: Int,
    isTextFieldFocused: Boolean = false,
    textFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    onSubjectNameChanged: (String) -> Unit = {},
    onTextFieldFocusChanged : (Boolean) -> Unit = {},
    onModifyBtnClicked: (Int, String) -> Unit = {_, _ ->},
    onDeleteBtnClicked: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize().addFocusCleaner(focusManager = focusManager)
    ) {

        BbangZipBaseTopBar(
            title = "과목명 수정하기",
            leadingIcon = R.drawable.ic_chevronleft_thick_small_24
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 20.dp)
        ) {
            Text(
                text = "과목명",
                style = BbangZipTheme.typography.body1Bold,
                color = BbangZipTheme.colors.labelNormal_282119
            )

            Spacer(modifier = Modifier.height(16.dp))

            BbangZipBasicTextField(
                leadingIcon = R.drawable.ic_book_default_24,
                placeholder = R.string.modify_subject_name_placeholder,
                guideline = R.string.modify_subject_name_guideline,
                value = subjectName,
                modifier = Modifier.fillMaxWidth(),
                bbangZipTextFieldInputState = textFieldInputState,
                onValueChange = onSubjectNameChanged,
                onFocusChange = onTextFieldFocusChanged,
                onDeleteButtonClick = onDeleteBtnClicked,
                maxCharacter = 10,
                focusManager = focusManager
            )

            Spacer(modifier = Modifier.weight(1f))

            BbangZipButton(
                bbangZipButtonSize = BbangZipButtonSize.Large,
                bbangZipButtonType = BbangZipButtonType.Solid,
                onClick = {
                    onModifyBtnClicked(subjectId, subjectName)
                },
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.btn_modify_label),
                isEnable = isButtonEnable
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ModifySubjectNameScreenPreview() {
    ModifySubjectNameScreen(subjectId = 0)
}
