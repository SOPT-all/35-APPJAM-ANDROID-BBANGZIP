package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.repository.remote.UserRepository

class DeleteWithdrawUseCase (
    private val userRepository: UserRepository
){
    suspend operator fun invoke(): Result<String> =
        userRepository.withdraw()
}