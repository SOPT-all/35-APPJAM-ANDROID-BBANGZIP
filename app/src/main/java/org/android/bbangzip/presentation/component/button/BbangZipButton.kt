package org.android.bbangzip.presentation.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.android.bbangzip.R
import org.android.bbangzip.presentation.type.BbangZipButtonSize
import org.android.bbangzip.presentation.type.BbangZipButtonType
import org.android.bbangzip.presentation.util.modifier.applyFilterOnClick
import org.android.bbangzip.ui.theme.BBANGZIPTheme

@Composable
fun BbangZipButton(
    bbangZipButtonType: BbangZipButtonType,
    bbangZipButtonSize: BbangZipButtonSize,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    isEnable: Boolean = true,
) {
    val backgroundColor = if (isEnable) bbangZipButtonType.enableBackgroundColor else bbangZipButtonType.disableBackgroundColor
    val contentColor = if (isEnable) bbangZipButtonType.enableContentColor else bbangZipButtonType.disableContentColor

    BbangZipButtonSlot(
        modifier =
            modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(bbangZipButtonSize.cornerRadius))
                .border(color = bbangZipButtonType.enableBorderColor, width = bbangZipButtonType.borderWidth, shape = RoundedCornerShape(bbangZipButtonSize.cornerRadius))
                .applyFilterOnClick(
                    baseColor = backgroundColor,
                    radius = bbangZipButtonSize.cornerRadius,
                    isDisabled = !isEnable,
                    onClick = {
                        if (isEnable) onClick()
                    },
                )
                .padding(
                    horizontal = bbangZipButtonSize.horizontalPadding,
                    vertical = bbangZipButtonSize.verticalPadding,
                ),
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(leadingIcon),
                    modifier = Modifier.size(size = bbangZipButtonSize.iconSize),
                    contentDescription = null,
                    tint = contentColor,
                )
                Spacer(modifier = Modifier.width(bbangZipButtonSize.spacing))
            }
        },
        label = {
            Text(
                text = label,
                style = bbangZipButtonSize.textStyle,
                color = contentColor,
            )
        },
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(trailingIcon),
                    modifier = Modifier.size(size = bbangZipButtonSize.iconSize),
                    contentDescription = null,
                    tint = contentColor,
                )
                Spacer(modifier = Modifier.width(bbangZipButtonSize.spacing))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BbangZipButtonPreview() {
    var addTodo by remember { mutableStateOf(true) }
    var overDueCount by remember { mutableIntStateOf(1) }
    BBANGZIPTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { },
                label = stringResource(R.string.btn_next_label),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = R.drawable.ic_chevronright_thick_small_24,
            )

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Solid,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { addTodo = !addTodo },
                label = stringResource(R.string.btn_add_todo_label),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = R.drawable.ic_chevronright_thick_small_24,
                isEnable = addTodo,
            )

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Kakao,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { },
                label = stringResource(R.string.btn_kakao_login_label),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.ic_kakao_default_24,
            )

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Outlined,
                bbangZipButtonSize = BbangZipButtonSize.Large,
                onClick = { },
                label = stringResource(R.string.btn_cancle_label),
                modifier = Modifier.fillMaxWidth(),
            )

            BbangZipButton(
                bbangZipButtonType = BbangZipButtonType.Outlined,
                bbangZipButtonSize = BbangZipButtonSize.Medium,
                onClick = { overDueCount += 1 },
                label = String.format(stringResource(R.string.btn_todo_overdue_label), overDueCount),
                trailingIcon = R.drawable.ic_chevronright_thick_small_24,
                modifier = Modifier.wrapContentSize(),
            )
        }
    }
}
