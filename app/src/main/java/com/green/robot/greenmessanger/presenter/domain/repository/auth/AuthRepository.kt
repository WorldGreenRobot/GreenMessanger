package com.green.robot.greenmessanger.presenter.domain.repository.auth

interface AuthRepository {
    fun createAuth(email: String, phone: String)
    fun checkAuth();
}