package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class OnboardingType(
    @StringRes val title: Int,
    @StringRes val description: Int? = null,
) {
    FIRST(
        title = R.string.onboarding_first_title,
    ),
    SECOND(
        title = R.string.onboarding_second_title,
        description = R.string.onboarding_second_description,
    ),
    THIRD(
        title = R.string.onboarding_third_title,
        description = R.string.onboarding_third_description,
    ),
}
