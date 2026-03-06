package com.green.robot.greenmessanger.presenter.data.impl

import com.green.robot.greenmessanger.presenter.domain.repository.auth.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {
    override fun createAuth(email: String, phone: String) {
        TODO("Not yet implemented")
    }

    override fun checkAuth() {
        TODO("Not yet implemented")
    }
}