package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.repository.remote.UserRepository

class DeleteLogoutUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<String> =
        userRepository.logout()
}
