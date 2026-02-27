package com.green.robot.greenmessanger.presenter.presenter.chats

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.green.robot.greenmessanger.presenter.domain.entity.user.User
import com.green.robot.greenmessanger.presenter.presenter.components.HeaderDrawer
import com.green.robot.greenmessanger.presenter.presenter.components.NavigationDrawer
import com.green.robot.greenmessanger.presenter.presenter.components.Screen
import com.green.robot.greenmessanger.presenter.presenter.components.ScreenDrawer
import com.green.robot.greenmessanger.presenter.presenter.navigation.Navigator
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme
import kotlinx.coroutines.launch

@Composable
fun ChatsScreen(
    navigator: Navigator,
    viewModel: ChatsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    ChatsContent(
        state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsContent(
    state: ChatsUiState,
    modifier: Modifier = Modifier
) {

    LocalContext.current
    ScreenDrawer(
        modifier = modifier.fillMaxSize(),
        drawerContent = {
            Spacer(Modifier.height(12.dp))
            state.user?.let {
                HeaderDrawer(
                    user = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)


        }
    ) {

    }
}

@Composable
@Preview(showBackground = true)
private fun ChatsScreenPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                user = User(
                    id = "",
                    name = "Пользователь",
                    email = "your@mail.com",
                    image = Uri.EMPTY
                )
            )
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
private fun ChatsScreenDarkPreview() {
    GreenMessangerTheme {
        ChatsContent(
            state = ChatsUiState(
                user = User(
                    id = "",
                    name = "Пользователь",
                    email = "your@mail.com",
                    image = Uri.EMPTY
                )
            )
        )
    }
}