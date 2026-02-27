package com.green.robot.greenmessanger.presenter.domain.entity

import java.util.Date

data class TextMessage(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val date: Date,
    val isSender: Boolean,
    val isRead: Boolean,
) : Message()