package com.green.robot.greenmessanger.presenter.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.green.robot.greenmessanger.presenter.domain.entity.chats.Chat
import com.green.robot.greenmessanger.presenter.presenter.chat.ChatScreen
import com.green.robot.greenmessanger.presenter.presenter.chats.ChatsScreen
import com.green.robot.greenmessanger.presenter.presenter.navigation.ChatNavigate
import com.green.robot.greenmessanger.presenter.presenter.navigation.Chats
import com.green.robot.greenmessanger.presenter.presenter.navigation.Contacts
import com.green.robot.greenmessanger.presenter.presenter.navigation.CreateGroup
import com.green.robot.greenmessanger.presenter.presenter.navigation.CreateSecretChat
import com.green.robot.greenmessanger.presenter.presenter.navigation.Helps
import com.green.robot.greenmessanger.presenter.presenter.navigation.Navigator
import com.green.robot.greenmessanger.presenter.presenter.navigation.QuiestionAboutTelegram
import com.green.robot.greenmessanger.presenter.presenter.navigation.Settings
import com.green.robot.greenmessanger.presenter.presenter.navigation.rememberNavigationState
import com.green.robot.greenmessanger.presenter.presenter.navigation.toEntries
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navigationState = rememberNavigationState(
                startRoute = Chats,
                topLevelRoutes = setOf(Chats)
            )

            val navigator = remember { Navigator(navigationState) }

            val entryProvider = entryProvider {
                entry<Chats> { _ -> ChatsScreen(navigator) }
                entry<ChatNavigate> { key -> ChatScreen(key.id, navigator) }
                entry<CreateGroup> { _ -> ChatsScreen(navigator) }
                entry<CreateSecretChat> { _ -> ChatsScreen(navigator) }
                entry<Contacts> { _ -> ChatsScreen(navigator) }
                entry<Settings> { _ -> ChatsScreen(navigator) }
                entry<Helps> { _ -> ChatsScreen(navigator) }
                entry<QuiestionAboutTelegram> { _ -> ChatsScreen(navigator) }
            }

            GreenMessangerTheme {
                NavDisplay(
                    entries = navigationState.toEntries(entryProvider),
                    onBack = { navigator.goBack() },
                    sceneStrategy = remember { DialogSceneStrategy() },
                    transitionSpec = {
                        slideInHorizontally(initialOffsetX = { it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { -it })
                    },
                    popTransitionSpec = {
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                    predictivePopTransitionSpec = {
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                )
            }

        }
    }
}