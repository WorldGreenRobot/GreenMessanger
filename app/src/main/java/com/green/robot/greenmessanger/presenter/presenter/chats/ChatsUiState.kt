package com.green.robot.greenmessanger.presenter.presenter.chats

import com.green.robot.greenmessanger.presenter.domain.entity.chats.Chat
import com.green.robot.greenmessanger.presenter.domain.entity.chats.SkeletonData
import com.green.robot.greenmessanger.presenter.domain.entity.user.User

data class ChatsUiState(
    val showLoading: Boolean = false,
    val chats: List<Chat>? = null,
    val user: User? = null,
    val error: String? = null
) {
    val showSkeleton: Boolean
        get() {
            return if (chats == null) {
                true
            } else {
                val isSkeleton = chats.firstOrNull { it is SkeletonData } != null
                isSkeleton
            } && user == null
        }
}