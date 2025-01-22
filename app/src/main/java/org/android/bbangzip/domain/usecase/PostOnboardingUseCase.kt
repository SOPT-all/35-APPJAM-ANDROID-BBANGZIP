package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.OnboardingEntity
import org.android.bbangzip.domain.repository.remote.UserRepository

class PostOnboardingUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        onboardingEntity: OnboardingEntity,
    ): Result<String> =
        userRepository.onboardingComplete(onboardingEntity = onboardingEntity)
}
