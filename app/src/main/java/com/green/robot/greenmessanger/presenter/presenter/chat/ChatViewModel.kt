package com.green.robot.greenmessanger.presenter.presenter.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.green.robot.greenmessanger.presenter.domain.usecase.GetChatByIdChatUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    @Assisted("id") private val id: String,
    private val getChatByIdChatUseCase: GetChatByIdChatUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                showLoading = uiState.value.messages != null,
                showSkeleton = uiState.value.messages == null,
                error = null
            )
            try {
                getChatByIdChatUseCase(id)
                    .collect {
                        when {
                            it.isSuccess -> {
                                _uiState.value = uiState.value.copy(
                                    showLoading = false,
                                    showSkeleton = false,
                                    messages = it.getOrNull().orEmpty()
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

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("id") id: String): ChatViewModel
    }
}