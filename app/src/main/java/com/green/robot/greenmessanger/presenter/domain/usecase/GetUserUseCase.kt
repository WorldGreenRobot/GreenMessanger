package com.green.robot.greenmessanger.presenter.domain.usecase

import android.net.Uri
import com.green.robot.greenmessanger.presenter.domain.entity.user.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor() {
    suspend operator fun invoke(): Result<User> {
        return Result.success(User(
            id = "1",
            name = "User",
            email = "james.francis.byrnes@example-pet-store.com",
            image = Uri.EMPTY
        ))
    }
}