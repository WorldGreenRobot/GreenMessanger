package com.green.robot.greenmessanger.presenter.data.impl

import com.google.firebase.auth.FirebaseAuth
import com.green.robot.greenmessanger.presenter.domain.repository.user.UserRepository
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : UserRepository {
    override fun createUser(email: String, password: String) {


    }

    override fun authorization(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}