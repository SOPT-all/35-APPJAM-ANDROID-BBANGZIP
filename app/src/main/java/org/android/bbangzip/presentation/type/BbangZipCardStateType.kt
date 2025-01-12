package org.android.bbangzip.presentation.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.android.bbangzip.presentation.model.ShadowConfig
import org.android.bbangzip.ui.theme.BbangZipTheme

data class StudyCardStyle(
    val background: Color,
    val borderColor: Color,
    val borderWidth: Dp,
    val radius: Dp,
    val shadowOptions: List<ShadowConfig>
)

sealed interface BbangZipCardStateType {
    @Composable fun getStyle(): StudyCardStyle

    data object DEFAULT : BbangZipCardStateType {
        @Composable override fun getStyle() = StudyCardStyle(
            background = BbangZipTheme.colors.backgroundNormal_FFFFFF,
            borderColor = BbangZipTheme.colors.lineAlternative_68645E_08,
            borderWidth = 1.dp,
            radius = 24.dp,
            shadowOptions = listOf(
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity8),
                    blur = 8.dp,
                    offsetX = 0.dp,
                    offsetY = 2.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity8),
                    blur = 4.dp,
                    offsetX = 0.dp,
                    offsetY = 1.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity8),
                    blur = 1.dp,
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    spread = 0.dp
                )
            )
        )
    }

    data object CHECKABLE : BbangZipCardStateType {
        @Composable override fun getStyle() = StudyCardStyle(
            background = BbangZipTheme.colors.backgroundAlternative_F5F5F5,
            borderColor = BbangZipTheme.colors.lineAlternative_68645E_08,
            borderWidth = 1.dp,
            radius = 24.dp,
            shadowOptions = listOf(
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 12.dp,
                    offsetX = 0.dp,
                    offsetY = 6.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 8.dp,
                    offsetX = 0.dp,
                    offsetY = 4.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 4.dp,
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    spread = 0.dp
                )
            )
        )
    }

    data object CHECKED : BbangZipCardStateType {
        @Composable override fun getStyle() = StudyCardStyle(
            background = BbangZipTheme.colors.backgroundAlternative_F5F5F5,
            borderColor = BbangZipTheme.colors.lineStrong_68645E_52,
            borderWidth = 3.dp,
            radius = 24.dp,
            shadowOptions = listOf(
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 12.dp,
                    offsetX = 0.dp,
                    offsetY = 6.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 8.dp,
                    offsetX = 0.dp,
                    offsetY = 4.dp,
                    spread = 0.dp
                ),
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity12),
                    blur = 4.dp,
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    spread = 0.dp
                )
            )
        )
    }
}

sealed interface ToDoCardStateType : BbangZipCardStateType {
    data object COMPLETE : ToDoCardStateType {
        @Composable override fun getStyle() = StudyCardStyle(
            background = BbangZipTheme.colors.backgroundNormal_FFFFFF,
            borderColor = BbangZipTheme.colors.lineAlternative_68645E_08,
            borderWidth = 1.dp,
            radius = 24.dp,
            shadowOptions = listOf(
                ShadowConfig(
                    color = BbangZipTheme.colors.staticBlack_000000.copy(BbangZipTheme.opacity.opacity28),
                    blur = 4.dp,
                    offsetX = 0.dp,
                    offsetY = 4.dp,
                    spread = 0.dp
                )
            )
        )
    }
}
