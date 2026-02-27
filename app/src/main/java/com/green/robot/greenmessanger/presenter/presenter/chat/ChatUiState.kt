package com.green.robot.greenmessanger.presenter.presenter.chat

import com.green.robot.greenmessanger.presenter.domain.entity.Message

data class ChatUiState(
    val showSkeleton: Boolean = true,
    val showLoading: Boolean = false,
    val messages: List<Message>? = null,
    val error: String? = null
)