package org.android.bbangzip.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.android.bbangzip.presentation.model.ShadowConfig

sealed interface StudyCardStateType {
    data class DEFAULT(
        var background: Color,
        var borderColor: Color,
        var borderWidth: Dp,
        var radius: Dp,
        var shadowOptions: List<ShadowConfig>
    ) : StudyCardStateType


    data class CHECKABLE(
        var background: Color,
        var borderColor: Color,
        var borderWidth: Dp,
        var radius: Dp,
        var shadowOptions: List<ShadowConfig>
    ) : StudyCardStateType

    data class CHECKED(
        var background: Color,
        var borderColor: Color,
        var borderWidth: Dp,
        var radius: Dp,
        var shadowOptions: List<ShadowConfig>
    ) : StudyCardStateType
}

sealed interface SubjectCardStateType : StudyCardStateType

sealed interface ToDoCardStateType : StudyCardStateType {
    data class COMPLETE(
        var background: Color,
        var borderColor: Color,
        var borderWidth: Dp,
        var radius: Dp,
        var shadowOptions : ShadowConfig
    ) : ToDoCardStateType
}