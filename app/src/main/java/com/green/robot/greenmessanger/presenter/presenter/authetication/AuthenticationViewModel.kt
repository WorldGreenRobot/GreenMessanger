package com.green.robot.greenmessanger.presenter.presenter.authetication

import androidx.lifecycle.ViewModel
import com.green.robot.greenmessanger.presenter.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun changeStateRegistration(changeRegistrationState: RegistrationState) {
        _uiState.value = uiState.value.copy(
            state = changeRegistrationState
        )
    }

    fun registration() {
        try {
            val email = uiState.value.email?.value.orEmpty()
            val password = uiState.value.password?.value.orEmpty()
            createUserUseCase(email, password)
            _uiState.update {
                it.copy(
                    state = RegistrationState.AUTHORIZATION
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    error = e.message
                )
            }
        }
    }

    fun authorization() {

    }
}