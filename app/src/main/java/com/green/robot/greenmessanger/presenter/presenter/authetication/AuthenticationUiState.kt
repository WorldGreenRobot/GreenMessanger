package com.green.robot.greenmessanger.presenter.presenter.authetication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.green.robot.greenmessanger.R

data class AuthenticationUiState(
    val email: String = "",
    val password: String = "",
    val state: RegistrationState = RegistrationState.AUTHORIZATION
)

enum class RegistrationState {
    REGISTRATION,
    AUTHORIZATION;


    @ReadOnlyComposable
    @Composable
    fun getButtonText(): String {
        return when (this) {
            REGISTRATION -> stringResource(R.string.sing_up)
            AUTHORIZATION -> stringResource(R.string.login)
        }
    }

    @ReadOnlyComposable
    @Composable
    fun getTitleText(): String {
        return when(this) {
            REGISTRATION -> stringResource(R.string.registration)
            AUTHORIZATION -> stringResource(R.string.authorization)

        }
    }

    @ReadOnlyComposable
    @Composable
    fun getSingInLogin(): String {
        return when(this) {
            REGISTRATION -> stringResource(R.string.login)
            AUTHORIZATION -> stringResource(R.string.sing_up)
        }
    }
}
