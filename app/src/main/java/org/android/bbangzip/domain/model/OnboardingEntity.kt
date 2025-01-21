package org.android.bbangzip.domain.model

import org.android.bbangzip.data.dto.request.RequestOnboardingDto

data class OnboardingEntity(
    val nickname: String,
    val year: Int,
    val semester: String,
    val subjectName: String
) {
    fun toOnboardingInfoDto() = RequestOnboardingDto(
        nickname = nickname,
        year = year,
        semester = semester,
        subjectName = subjectName
    )
}
