package org.android.bbangzip.presentation.type

import androidx.annotation.StringRes
import org.android.bbangzip.R

enum class OnboardingType(
    @StringRes val title: Int,
    @StringRes val description: Int? = null,
    @StringRes val placeholder: Int? = null,
    @StringRes val guideline: Int? = null,
    @StringRes val success: Int? = null,
) {
    FIRST(
        title = R.string.onboarding_first_title,
        placeholder = R.string.onboarding_name_placeholder,
        guideline = R.string.onboarding_name_description,
        success = R.string.onboarding_name_success,
    ),
    SECOND(
        title = R.string.onboarding_second_title,
        description = R.string.onboarding_second_description,
    ),
    THIRD(
        title = R.string.onboarding_third_title,
        description = R.string.onboarding_third_description,
        placeholder = R.string.onboarding_subject_placeholder,
        guideline = R.string.onboarding_subject_description,
        success = R.string.onboarding_subject_success,
    ),
}
