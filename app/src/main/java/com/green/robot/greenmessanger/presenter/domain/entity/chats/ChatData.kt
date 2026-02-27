package com.green.robot.greenmessanger.presenter.domain.entity.chats

import android.net.Uri
import java.util.Date

data class ChatData(
    val id: String,
    val title: String,
    val lastMessage: String,
    val date: Date,
    val image: Uri,
    val countNotReadingMessage: Int
) : Chat
