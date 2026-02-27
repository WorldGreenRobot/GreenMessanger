package com.green.robot.greenmessanger.presenter.presenter.chats

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import coil3.compose.SubcomposeAsyncImage
import com.green.robot.greenmessanger.R
import com.green.robot.greenmessanger.presenter.domain.entity.chats.Chat
import com.green.robot.greenmessanger.presenter.domain.entity.user.User
import com.green.robot.greenmessanger.presenter.presenter.components.EmptyProfile
import com.green.robot.greenmessanger.presenter.presenter.components.NavigationDrawerContent
import com.green.robot.greenmessanger.presenter.presenter.components.ScreenDrawer
import com.green.robot.greenmessanger.presenter.presenter.navigation.ChatNavigate
import com.green.robot.greenmessanger.presenter.presenter.navigation.Navigator
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun ChatsScreen(
    navigator: Navigator,
    viewModel: ChatsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ChatsContent(
        state = state,
        modifier = Modifier.fillMaxSize(),
        onAction = remember {
            { action ->
                handlerAction(navigator, action, viewModel)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsContent(
    state: ChatsUiState,
    modifier: Modifier = Modifier,
    onAction: (ChatsAction) -> Unit = {},
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ScreenDrawer(
        modifier = modifier.fillMaxSize(),
        drawerState = drawerState,
        onRefresh = {
            onAction(ChatsAction.Refresh)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24dp),
                            contentDescription = stringResource(R.string.menu)
                        )
                    }
                }
            )
        },
        drawerContent = {
            NavigationDrawerContent(
                profile = state.user,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                onNavigate = {
                    onAction(ChatsAction.Navigate(it))
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                state.showSkeleton -> {
                    items(10) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .background(MaterialTheme.colorScheme.onSurface)
                        )
                    }
                }

                else -> {
                    items(state.chats.orEmpty(), key = { it.id }) {
                        ChatItem(
                            chat = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAction(ChatsAction.Navigate(ChatNavigate(it.id)))
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatItem(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(40.dp),
            model = chat.image,
            error = {
                EmptyProfile()
            },
            loading = {

            },
            contentDescription = null,
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = chat.title,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = chat.lastMessage,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun handlerAction(navigator: Navigator, action: ChatsAction, viewModel: ChatsViewModel) {
    when (action) {
        is ChatsAction.Navigate -> {
            navigator.navigate(action.key)
        }

        is ChatsAction.Refresh -> {
            viewModel.refresh()
        }
    }
}

sealed interface ChatsAction {
    data class Navigate(val key: NavKey) : ChatsAction
    object Refresh : ChatsAction

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ChatsScreenPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                showSkeleton = false,
                user = User(
                    id = "",
                    name = "Пользователь",
                    email = "your@mail.com",
                    image = Uri.EMPTY
                ),
                chats = List(20) {
                    Chat(
                        id = it.toString(),
                        title = "Chat $it",
                        lastMessage = "Last message $it",
                        date = Calendar.getInstance().time,
                        image = Uri.EMPTY,
                        countNotReadingMessage = it
                    )
                }
            )
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
private fun ChatsScreenDarkPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                showSkeleton = false,
                user = User(
                    id = "",
                    name = "Пользователь",
                    email = "your@mail.com",
                    image = Uri.EMPTY
                ),
                chats = List(20) {
                    Chat(
                        id = it.toString(),
                        title = "Chat $it",
                        lastMessage = "Last message $it",
                        date = Calendar.getInstance().time,
                        image = Uri.EMPTY,
                        countNotReadingMessage = it
                    )
                }
            )
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ChatsScreenSkeletonPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                showSkeleton = true,
            )
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
private fun ChatsScreenSkeletonDarkPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                showSkeleton = true,
            )
        )
    }
}