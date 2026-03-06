package com.green.robot.greenmessanger.presenter.domain.usecase

import com.green.robot.greenmessanger.presenter.domain.repository.user.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String, password: String) {
        userRepository.createUser(email, password)
    }
}