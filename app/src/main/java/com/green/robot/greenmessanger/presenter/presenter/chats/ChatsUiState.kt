package com.green.robot.greenmessanger.presenter.presenter.chats

import com.green.robot.greenmessanger.presenter.domain.entity.chats.Chat
import com.green.robot.greenmessanger.presenter.domain.entity.user.User

data class ChatsUiState(
    val showLoading: Boolean = false,
    val showSkeleton: Boolean = true,
    val chats: List<Chat>? = null,
    val user: User? = null,
    val error: String? = null
)