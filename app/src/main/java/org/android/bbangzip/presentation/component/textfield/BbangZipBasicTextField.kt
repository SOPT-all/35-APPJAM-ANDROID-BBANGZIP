package org.android.bbangzip.presentation.component.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.getBackgroundColor
import org.android.bbangzip.presentation.model.getBorderColor
import org.android.bbangzip.presentation.model.getCharacterCheckColor
import org.android.bbangzip.presentation.model.getGuidelineColor
import org.android.bbangzip.presentation.model.getIconColor
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import timber.log.Timber

@Composable
fun BbangZipBasicTextField(
    @DrawableRes leadingIcon: Int,
    @StringRes placeholder: Int,
    @StringRes guideline: Int,
    value: String,
    onValueChange: (String) -> Unit,
    maxCharacter: Int,
    modifier: Modifier = Modifier,
    bbangZipTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    onFocusChange: (Boolean) -> Unit = { },
    onDeleteButtonClick: () -> Unit = { },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
    focusManager: FocusManager,
    keyboardActions: KeyboardActions =
        KeyboardActions(
            onDone = {
                val trimmedValue = value.trim()
                onValueChange(trimmedValue)
                focusManager.clearFocus(force = true)
            },
        ),
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    BbangZipTextFieldSlot(
        columnModifier = modifier,
        rowModifier =
            Modifier
                .background(color = bbangZipTextFieldInputState.getBackgroundColor(), shape = RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = bbangZipTextFieldInputState.getBorderColor(), shape = RoundedCornerShape(20.dp))
                .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                tint = bbangZipTextFieldInputState.getIconColor(),
            )
        },
        content = {
            BasicTextField(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            onFocusChange(focusState.isFocused)
                            Timber.d("[TextField] onFocusChange -> $isFocused")
                        }
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Enter && keyEvent.type == KeyEventType.KeyUp) {
                                focusManager.clearFocus(force = true)
                                onFocusChange(false)
                                true
                            } else {
                                false
                            }
                        },
                value = value,
                onValueChange = {
                    if (it.length <= maxCharacter) onValueChange(it)
                },
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
                cursorBrush = SolidColor(BbangZipTheme.colors.labelNormal_282119),
                singleLine = true,
                textStyle = BbangZipTheme.typography.label1Medium,
                decorationBox = { innerTextField ->
                    innerTextField()

                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(placeholder),
                            color = BbangZipTheme.colors.labelAssistive_282119_28,
                            style = BbangZipTheme.typography.label1Medium,
                        )
                    }
                },
            )
        },
        characterCount = {
            Text(
                text = stringResource(R.string.textfield_character_counter, value.length.toString(), maxCharacter.toString()),
                color = bbangZipTextFieldInputState.getCharacterCheckColor(),
                style = BbangZipTheme.typography.caption1Medium,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier =
                        Modifier
                            .padding(start = 8.dp)
                            .noRippleClickable { onDeleteButtonClick() },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_x_circle_default_24),
                    tint = bbangZipTextFieldInputState.getIconColor(),
                    contentDescription = null,
                )
            }
        },
        guideline = {
            Text(
                text = stringResource(id = guideline),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                color = bbangZipTextFieldInputState.getGuidelineColor(),
                style = BbangZipTheme.typography.caption2Medium,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun BbangZipBasicTextFieldPreview() {
    BBANGZIPTheme {
        var text by remember { mutableStateOf("") }
        var validationState by remember { mutableStateOf<BbangZipTextFieldInputState>(BbangZipTextFieldInputState.Default) }

        fun validateText(text: String) {
            validationState =
                when {
                    text.isEmpty() -> BbangZipTextFieldInputState.Default
                    text.length == 1 -> BbangZipTextFieldInputState.Typing
                    text.length == 3 -> BbangZipTextFieldInputState.Placeholder
                    text.length > 5 -> BbangZipTextFieldInputState.Alert
                    else -> BbangZipTextFieldInputState.Field
                }
        }

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
    }
}
