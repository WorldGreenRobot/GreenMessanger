package com.green.robot.greenmessanger.presenter.domain.repository.user

interface UserRepository {
    fun createUser(email: String, password: String)

    fun authorization(email: String, password: String)
}