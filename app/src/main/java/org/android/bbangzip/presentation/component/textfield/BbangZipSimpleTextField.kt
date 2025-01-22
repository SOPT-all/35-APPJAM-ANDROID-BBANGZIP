package org.android.bbangzip.presentation.component.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.getBackgroundColor
import org.android.bbangzip.presentation.model.getBorderColor
import org.android.bbangzip.presentation.model.getGuidelineColor
import org.android.bbangzip.presentation.model.getIconColor
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors
import timber.log.Timber

@Composable
fun BbangZipSimpleTextField(
    @DrawableRes leadingIcon: Int,
    @StringRes placeholder: Int,
    @StringRes guideline: Int,
    value: String,
    isEnable: Boolean = true,
    modifier: Modifier = Modifier,
    bbangZipTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    onFocusChange: (Boolean) -> Unit = { },
    onValueChange: (String) -> Unit = { },
    focusManager: FocusManager,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
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
    BbangZipTextFieldSlot(
        columnModifier = modifier,
        rowModifier =
            Modifier
                .background(color = bbangZipTextFieldInputState.getBackgroundColor(), shape = RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = bbangZipTextFieldInputState.getBorderColor(), shape = RoundedCornerShape(20.dp))
                .padding(start = 16.dp, end = 12.dp, top = 18.dp, bottom = 18.dp),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(leadingIcon),
                modifier = Modifier.size(size = 16.dp),
                contentDescription = null,
                tint = bbangZipTextFieldInputState.getIconColor(),
            )
        },
        content = {
            BasicTextField(
                enabled = isEnable,
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .focusRequester(FocusRequester())
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            onFocusChange(focusState.isFocused)
                            Timber.d("[TextField] onFocusChange -> $isFocused") }
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Enter && keyEvent.type == KeyEventType.KeyUp) {
                                focusManager.clearFocus(force = true)
                                onFocusChange(false)
                                true
                            } else {
                                false
                            }
                        }
                ,
                value = value,
                onValueChange = { input ->
                    val filteredInput = input.filter { it.isDigit() }
                    if (filteredInput.length <= 3) onValueChange(filteredInput)
                                },
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                cursorBrush = SolidColor(BbangZipTheme.colors.labelNormal_282119),
                singleLine = true,
                textStyle = BbangZipTheme.typography.label1Medium,
                decorationBox = { innerTextField ->
                    innerTextField()

                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(placeholder),
                            color = defaultBbangZipColors.labelAssistive_282119_28,
                            style = BbangZipTheme.typography.label1Medium,
                        )
                    }
                },
            )
        },
        guideline = {
            Text(
                text = stringResource(id = guideline),
                modifier =
                    Modifier
                        .padding(start = 8.dp, top = 4.dp),
                color =
                    bbangZipTextFieldInputState
                        .getGuidelineColor(),
                style = BbangZipTheme.typography.caption2Medium,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun BbangZipSimpleTextFieldPreview() {
    BBANGZIPTheme {
        var text by remember { mutableStateOf("") }
        var validationState by remember {
            mutableStateOf<BbangZipTextFieldInputState>(
                BbangZipTextFieldInputState.Default,
            )
        }

        fun validateText(text: String) {
            validationState =
                when {
                    text.isEmpty() -> BbangZipTextFieldInputState.Default
                    text.length == 1 -> BbangZipTextFieldInputState.Typing
                    5 < text.length -> BbangZipTextFieldInputState.Default
                    else -> BbangZipTextFieldInputState.Field
                }
        }

        BbangZipSimpleTextField(
            leadingIcon = R.drawable.ic_page_check_default_24,
            placeholder = R.string.app_name,
            guideline = R.string.app_name,
            value = text,
            bbangZipTextFieldInputState = validationState,
            onFocusChange = {
                if (validationState == BbangZipTextFieldInputState.Default) validationState = BbangZipTextFieldInputState.Typing else Unit
            },
            onValueChange = { newValue ->
                text = newValue
                validateText(text = newValue)
            },
            focusManager = LocalFocusManager.current
        )
    }
}
