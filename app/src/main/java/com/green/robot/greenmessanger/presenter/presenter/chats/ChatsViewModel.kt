package com.green.robot.greenmessanger.presenter.presenter.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.green.robot.greenmessanger.presenter.domain.usecase.GetChatsUseCase
import com.green.robot.greenmessanger.presenter.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatsUiState())
    val uiState = _uiState.asStateFlow()


    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = uiState.value.copy(
                    showLoading = uiState.value.chats != null || uiState.value.user != null,
                    showSkeleton = uiState.value.chats == null || uiState.value.user == null
                )
                val user = getUserUseCase()
                when {
                    user.isFailure -> {
                        _uiState.value = uiState.value.copy(
                            error = user.exceptionOrNull()?.message.orEmpty()
                        )
                    }

                    user.isSuccess -> {
                        val data = user.getOrNull()
                        data?.let { it ->
                            getChatsUseCase(it.id)
                                .collect {
                                    when {
                                        it.isSuccess -> {
                                            _uiState.value = uiState.value.copy(
                                                showLoading = false,
                                                showSkeleton = false,
                                                chats = it.getOrNull().orEmpty()
                                            )
                                        }

                                        it.isFailure -> {
                                            _uiState.value = uiState.value.copy(
                                                showLoading = false,
                                                error = it.exceptionOrNull()?.message.orEmpty()
                                            )
                                        }
                                    }
                                }
                        }
                    }
                }

            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    showLoading = false,
                    error = e.message.orEmpty()
                )
            }
        }
    }

    fun refresh() {
        loadData()
    }
}