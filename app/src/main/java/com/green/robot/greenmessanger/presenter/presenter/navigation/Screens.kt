package com.green.robot.greenmessanger.presenter.presenter.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object Chats : NavKey

@Serializable
data class Chat(val id: String) : NavKey
