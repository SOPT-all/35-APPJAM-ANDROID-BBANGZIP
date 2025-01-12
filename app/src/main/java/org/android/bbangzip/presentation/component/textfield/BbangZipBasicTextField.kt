package org.android.bbangzip.presentation.component.textfield

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    bbangZipBasicTextFieldInputState: BbangZipBasicTextFieldInputState = BbangZipBasicTextFieldInputState.Empty,
    onValueChange: (String) -> Unit = { },
    onDeleteButtonClick: () -> Unit = { },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = bbangZipBasicTextFieldInputState.getBackgroundColor(), shape = RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = bbangZipBasicTextFieldInputState.getBorderColor(), shape = RoundedCornerShape(20.dp))
                .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        ) {
            BbangZipBasicTextFieldSlot(
                leadingIcon = {
                    Icon(
                        painter = painterResource(leadingIcon),
                        contentDescription = null,
                        tint = bbangZipBasicTextFieldInputState.getIconColor()
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
                                    color = bbangZipBasicTextFieldInputState.getTextColor(),
                                    style = BbangZipTheme.typography.label1Medium
                                )
                            }
                        }
                    )
                },
                characterCount = {
                    Text(
                        text = value.length.toString(),
                        color = bbangZipBasicTextFieldInputState.getCharacterCheckColor(),
                        style = BbangZipTheme.typography.caption1Medium
                    )

                    Text(
                        text = "/",
                        color = bbangZipBasicTextFieldInputState.getCharacterCheckColor(),
                        style = BbangZipTheme.typography.caption1Medium
                    )

                    Text(
                        text = maxCharacter.toString(),
                        color = bbangZipBasicTextFieldInputState.getCharacterCheckColor(),
                        style = BbangZipTheme.typography.caption1Medium
                    )
                },
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        Icon(
                            modifier = Modifier
                                .noRippleClickable { onDeleteButtonClick() },
                            imageVector = ImageVector.vectorResource(R.drawable.ic_x_circle_default_24),
                            tint = bbangZipBasicTextFieldInputState.getIconColor(),
                            contentDescription = null
                        )
                    }
                },
            )
        }

        Text(
            text = stringResource(id = guideline),
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            color = bbangZipBasicTextFieldInputState.getGuidelineColor(),
            style = BbangZipTheme.typography.label1Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BbangZipBasicTextFieldPreview() {
    BBANGZIPTheme {
        var text by remember { mutableStateOf("") }
        var validationState by remember { mutableStateOf<BbangZipBasicTextFieldInputState>(BbangZipBasicTextFieldInputState.Empty) }

        fun validateText(text: String) {
            validationState = when {
                text.isEmpty() -> BbangZipBasicTextFieldInputState.Empty
                text.length == 1 -> BbangZipBasicTextFieldInputState.Typing
                5 < text.length -> BbangZipBasicTextFieldInputState.Error
                else -> BbangZipBasicTextFieldInputState.Success
            }
        }

        BbangZipBasicTextField(
            leadingIcon = R.drawable.ic_book_default_24,
            placeholder = R.string.app_name,
            guideline = R.string.app_name,
            value = text,
            maxCharacter = 20,
            bbangZipBasicTextFieldInputState = validationState,
            onValueChange = { newValue ->
                text = newValue
                validateText(text = newValue)
            },
            onDeleteButtonClick = {
                text = ""
                validationState = BbangZipBasicTextFieldInputState.Empty
            }
        )
    }
}