@file:OptIn(ExperimentalMaterial3Api::class)

package com.green.robot.greenmessanger.presenter.presenter.chat

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.green.robot.greenmessanger.R
import com.green.robot.greenmessanger.presenter.domain.entity.TextMessage
import com.green.robot.greenmessanger.presenter.presenter.components.Screen
import com.green.robot.greenmessanger.presenter.presenter.navigation.Navigator
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme
import java.util.Calendar

@Composable
fun ChatScreen(
    id: String,
    navigator: Navigator,
    viewModel: ChatViewModel = hiltViewModel<ChatViewModel, ChatViewModel.Factory>(key = id) { factory ->
        factory.create(id)
    }
) {
    val state: ChatUiState by viewModel.uiState.collectAsState()

    ChatContent(
        state = state,
        modifier = Modifier.fillMaxSize(),
        onAction = remember {
            {
                handlerAction(navigator, it, viewModel)
            }
        }
    )
}

@Composable
private fun ChatContent(
    state: ChatUiState,
    modifier: Modifier = Modifier,
    onAction: (ChatAction) -> Unit = {},
) {
    Screen(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Chat")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(ChatAction.OnBack)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_24dp),
                            contentDescription = stringResource(R.string.arrow_back)
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.messages.orEmpty()) {
                when (it) {
                    is TextMessage -> Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.content
                    )
                }
            }
        }
    }
}

private fun handlerAction(navigator: Navigator, action: ChatAction, viewModel: ChatViewModel) {
    when(action) {
        is ChatAction.OnBack -> {
            navigator.goBack()
        }
    }
}

sealed interface ChatAction {
    object OnBack: ChatAction
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ChatsScreenPreview() {
    GreenMessangerTheme {
        ChatContent(
            state = ChatUiState(
                showSkeleton = false,
                messages = List(30) {
                    TextMessage(
                        id = it.toString(),
                        senderId = it.toString(),
                        receiverId = it.toString(),
                        content = "Message $it",
                        date = Calendar.getInstance().time,
                        isSender = it % 2 == 0,
                        isRead = it % 2 == 0
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
        ChatContent(
            state = ChatUiState(
                showSkeleton = false,
                messages = List(30) {
                    TextMessage(
                        id = it.toString(),
                        senderId = it.toString(),
                        receiverId = it.toString(),
                        content = "Message $it",
                        date = Calendar.getInstance().time,
                        isSender = it % 2 == 0,
                        isRead = it % 2 == 0
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
        ChatContent(
            state = ChatUiState(
                showSkeleton = true,
            )
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
private fun ChatsScreenSkeletonDarkPreview() {
    GreenMessangerTheme {
        ChatContent(
            state = ChatUiState(
                showSkeleton = true,
            )
        )
    }
}