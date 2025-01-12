package org.android.bbangzip.presentation.component.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.util.modifier.noRippleClickable
import org.android.bbangzip.ui.theme.BBANGZIPTheme
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun BbangZipBasicTextField(
    @DrawableRes leadingIcon: Int,
    @StringRes placeholder: Int,
    @StringRes guideline: Int,
    value: String,
    maxCharacter: Int,
    modifier: Modifier = Modifier,
    bbangZipTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Empty,
    onValueChange: (String) -> Unit = { },
    onDeleteButtonClick: () -> Unit = { },
) {
    BbangZipTextFieldSlot(
        modifier = modifier
            .fillMaxWidth()
            .background(color = bbangZipTextFieldInputState.getBackgroundColor(), shape = RoundedCornerShape(20.dp))
            .border(width = 1.dp, color = bbangZipTextFieldInputState.getBorderColor(), shape = RoundedCornerShape(20.dp))
            .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = bbangZipTextFieldInputState.getIconColor()
            )
        },
        content = {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                value = value,
                onValueChange = {
                    if (it.length <= maxCharacter) onValueChange(it)
                },
                cursorBrush = SolidColor(BbangZipTheme.colors.labelNormal_282119),
                singleLine = true,
                textStyle = BbangZipTheme.typography.label1Medium,
                decorationBox = { innerTextField ->
                    innerTextField()

                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(placeholder),
                            color = bbangZipTextFieldInputState.getTextColor(),
                            style = BbangZipTheme.typography.label1Medium
                        )
                    }
                }
            )
        },
        characterCount = {
            Text(
                text = value.length.toString(),
                color = bbangZipTextFieldInputState.getCharacterCheckColor(),
                style = BbangZipTheme.typography.caption1Medium
            )

            Text(
                text = "/",
                color = bbangZipTextFieldInputState.getCharacterCheckColor(),
                style = BbangZipTheme.typography.caption1Medium
            )

            Text(
                text = maxCharacter.toString(),
                color = bbangZipTextFieldInputState.getCharacterCheckColor(),
                style = BbangZipTheme.typography.caption1Medium
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .noRippleClickable { onDeleteButtonClick() },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_x_circle_default_24),
                    tint = bbangZipTextFieldInputState.getIconColor(),
                    contentDescription = null
                )
            }
        },
        guideline = {
            Text(
                text = stringResource(id = guideline),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                color = bbangZipTextFieldInputState.getGuidelineColor(),
                style = BbangZipTheme.typography.caption2Medium
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BbangZipBasicTextFieldPreview() {
    BBANGZIPTheme {
        var text by remember { mutableStateOf("") }
        var validationState by remember { mutableStateOf<BbangZipTextFieldInputState>(BbangZipTextFieldInputState.Empty) }

        fun validateText(text: String) {
            validationState = when {
                text.isEmpty() -> BbangZipTextFieldInputState.Empty
                text.length == 1 -> BbangZipTextFieldInputState.Typing
                text.length == 3 -> BbangZipTextFieldInputState.Complete
                5 < text.length -> BbangZipTextFieldInputState.Error
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
            onDeleteButtonClick = {
                text = ""
                validationState = BbangZipTextFieldInputState.Empty
            }
        )
    }
}