package org.android.bbangzip.presentation.util.modifier

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.type.BbangZipShadowType

@Composable
fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier =
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
    )

fun Modifier.applyFilterOnClick(
    baseColor: Color = Color.Transparent,
    radius: Dp = 0.dp,
    isDisabled: Boolean = true,
    filterColor: Color = Color(0xFF282119).copy(alpha = 0.12f),
    onClick: () -> Unit = {},
): Modifier =
    composed {
        val finalFilteredColor =
            remember(baseColor, filterColor) {
                filterColor.compositeOver(baseColor)
            }
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        this
            .background(if (isPressed && !isDisabled) finalFilteredColor else baseColor, shape = RoundedCornerShape(size = radius))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() },
            )
    }

@Composable
fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp,
) = this.drawBehind {
    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint =
        Paint().apply {
            this.color = color
        }

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}

@Composable
fun Modifier.applyShadows(
    shadowType: BbangZipShadowType,
    shape: Shape,
): Modifier {
    return shadowType
        .shadowOptions
        .fold(this) { acc, shadowOption ->
            acc.dropShadow(
                shape = shape,
                color = shadowOption.color,
                blur = shadowOption.blur,
                offsetY = shadowOption.offsetY,
                offsetX = shadowOption.offsetX,
                spread = shadowOption.spread,
            )
        }
}

fun Modifier.fadingEdge(brush: Brush) =
    this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }