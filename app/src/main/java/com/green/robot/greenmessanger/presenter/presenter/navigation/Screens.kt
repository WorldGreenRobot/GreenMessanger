package com.green.robot.greenmessanger.presenter.presenter.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object Chats : NavKey

@Serializable
data class ChatNavigate(val id: String) : NavKey

@Serializable
object CreateGroup : NavKey

@Serializable
object CreateSecretChat : NavKey

@Serializable
object Contacts : NavKey

@Serializable
object Calls : NavKey

@Serializable
object Settings : NavKey

@Serializable
object Helps : NavKey

@Serializable
object QuiestionAboutTelegram : NavKey