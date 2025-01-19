package org.android.bbangzip.presentation.ui.friend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import org.android.bbangzip.R
import org.android.bbangzip.presentation.component.textfield.BbangZipBasicTextField
import org.android.bbangzip.presentation.component.textfield.BbangZipSimpleTextField
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.modifier.addFocusCleaner
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@Composable
fun FriendScreen(
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    var text by remember { mutableStateOf("") }
    var validationState by remember { mutableStateOf<BbangZipTextFieldInputState>(BbangZipTextFieldInputState.Default) }

    var text2 by remember { mutableStateOf("") }
    var validationState2 by remember {
        mutableStateOf<BbangZipTextFieldInputState>(
            BbangZipTextFieldInputState.Default,
        )
    }

    fun validateText(text: String) {
        validationState =
            when {
                text.isEmpty() -> BbangZipTextFieldInputState.Default
                text.length == 1 -> BbangZipTextFieldInputState.Typing
                text.length == 3 -> BbangZipTextFieldInputState.Field
                text.length > 5 -> BbangZipTextFieldInputState.Placeholder
                else -> BbangZipTextFieldInputState.Field
            }
    }

    fun validate2Text(text: String) {
        validationState2 =
            when {
                text.isEmpty() -> BbangZipTextFieldInputState.Default
                text.length == 1 -> BbangZipTextFieldInputState.Typing
                5 < text.length -> BbangZipTextFieldInputState.Placeholder
                else -> BbangZipTextFieldInputState.Field
            }
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .addFocusCleaner(focusManager),
    ) {
        BbangZipBasicTextField(
            leadingIcon = R.drawable.ic_book_default_24,
            placeholder = R.string.app_name,
            guideline = R.string.app_name,
            value = text,
            maxCharacter = 20,
            bbangZipTextFieldInputState = validationState,
            onValueChange = { newValue ->
                text = newValue
                validateText(text = newValue)
            },
            onFocusChange = {
                if (validationState == BbangZipTextFieldInputState.Default) validationState = BbangZipTextFieldInputState.Typing else Unit
            },
            onDeleteButtonClick = {
                text = ""
                validationState = BbangZipTextFieldInputState.Default
            },
            focusManager = LocalFocusManager.current,
        )

        Row {
            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                modifier = Modifier.weight(1f),
                placeholder = R.string.app_name,
                guideline = R.string.app_name,
                value = text2,
                bbangZipTextFieldInputState = validationState2,
                onFocusChange = {
                    if (validationState2 == BbangZipTextFieldInputState.Default) validationState2 = BbangZipTextFieldInputState.Typing else Unit
                },
                onValueChange = { newValue ->
                    text2 = newValue
                    validate2Text(text = newValue)
                },
            )

            BbangZipSimpleTextField(
                leadingIcon = R.drawable.ic_page_check_default_24,
                modifier = Modifier.weight(1f),
                placeholder = R.string.app_name,
                guideline = R.string.app_name,
                value = text2,
                bbangZipTextFieldInputState = validationState2,
                onFocusChange = {
                    if (validationState2 == BbangZipTextFieldInputState.Default) validationState2 = BbangZipTextFieldInputState.Typing else Unit
                },
                onValueChange = { newValue ->
                    text2 = newValue
                    validate2Text(text = newValue)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FriendScreenPreview() {
    BBANGZIPTheme {
    }
}
