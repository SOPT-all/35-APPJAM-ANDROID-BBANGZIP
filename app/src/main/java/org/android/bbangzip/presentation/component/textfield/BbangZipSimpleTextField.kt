package org.android.bbangzip.presentation.component.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme
import org.android.bbangzip.ui.theme.defaultBbangZipColors

@Composable
fun BbangZipSimpleTextField(
    @DrawableRes leadingIcon: Int,
    @StringRes placeholder: Int,
    @StringRes guideline: Int,
    value: String,
    modifier: Modifier = Modifier,
    bbangZipTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Empty,
    onClickTextField: () -> Unit = { },
    onValueChange: (String) -> Unit = { },
) {
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
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .focusRequester(FocusRequester())
                        .onFocusChanged { if (it.isFocused) onClickTextField() },
                value = value,
                onValueChange = { onValueChange(it) },
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
        var validationState by remember { mutableStateOf<BbangZipTextFieldInputState>(BbangZipTextFieldInputState.Empty) }

        fun validateText(text: String) {
            validationState =
                when {
                    text.isEmpty() -> BbangZipTextFieldInputState.Empty
                    text.length == 1 -> BbangZipTextFieldInputState.Typing
                    5 < text.length -> BbangZipTextFieldInputState.Error
                    else -> BbangZipTextFieldInputState.Field
                }
        }

        BbangZipSimpleTextField(
            leadingIcon = R.drawable.ic_page_check_default_24,
            placeholder = R.string.app_name,
            guideline = R.string.app_name,
            value = text,
            bbangZipTextFieldInputState = validationState,
            onClickTextField = {
            },
            onValueChange = { newValue ->
                text = newValue
                validateText(text = newValue)
            },
        )
    }
}
