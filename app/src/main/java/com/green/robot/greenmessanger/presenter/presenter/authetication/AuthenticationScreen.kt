@file:OptIn(ExperimentalMaterial3Api::class)

package com.green.robot.greenmessanger.presenter.presenter.authetication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.green.robot.greenmessanger.R
import com.green.robot.greenmessanger.presenter.presenter.components.Screen
import com.green.robot.greenmessanger.presenter.presenter.navigation.Navigator
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme

@Composable
fun AuthenticationScreen(
    navigator: Navigator,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    AuthenticationContent(
        state = state,
        onAction = remember {
            {
                handleAction(navigator, it, viewModel)
            }
        }
    )
}

@Composable
private fun AuthenticationContent(
    state: AuthenticationUiState,
    modifier: Modifier = Modifier,
    onAction: (AuthenticationAction) -> Unit
) {
    Screen(
        modifier = modifier.fillMaxSize(),
        isRefreshing = false
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = state.state.getTitleText()
            )

            Registration(
                email = state.email,
                password = state.password,
                modifier = Modifier
                    .fillMaxWidth(),
                onAction = onAction
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (state.state == RegistrationState.AUTHORIZATION) {
                    TextButton(
                        onClick = {},
                    ) {
                        Text(
                            text = stringResource(R.string.forgot_password)
                        )
                    }
                }

                TextButton(
                    onClick = {
                        if (state.state == RegistrationState.REGISTRATION) {
                            onAction(AuthenticationAction.ChangeAuthAndRegis(RegistrationState.AUTHORIZATION))
                        } else {
                            onAction(AuthenticationAction.ChangeAuthAndRegis(RegistrationState.REGISTRATION))
                        }
                    },
                ) {
                    Text(
                        text = state.state.getSingInLogin()
                    )
                }
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier,
                    text = state.state.getButtonText()
                )
            }
        }
    }
}

@Composable
private fun Registration(
    email: String,
    password: String,
    modifier: Modifier = Modifier,
    onAction: (AuthenticationAction) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                onAction(AuthenticationAction.ChangeEmail(it))
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                onAction(AuthenticationAction.ChangePassword(it))
            }
        )
    }
}

private fun handleAction(navigator: Navigator, action: AuthenticationAction, viewModel: AuthenticationViewModel) {
    when (action) {
        is AuthenticationAction.ChangePassword -> viewModel.updatePassword(action.password)
        is AuthenticationAction.ChangeEmail -> viewModel.updateEmail(action.email)
        is AuthenticationAction.ChangeAuthAndRegis -> viewModel.changeStateRegistration(action.state)
    }
}

sealed interface AuthenticationAction {
    data class ChangeEmail(val email: String) : AuthenticationAction
    data class ChangePassword(val password: String) : AuthenticationAction
    data class ChangeAuthAndRegis(val state: RegistrationState) : AuthenticationAction
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AuthenticationAuthorizationScreenPreview() {
    GreenMessangerTheme {
        AuthenticationContent(
            state = AuthenticationUiState(
                email = "email@email.com",
                password = "password"
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
private fun AuthenticationAuthorizationScreenDarkPreview() {
    GreenMessangerTheme {
        AuthenticationContent(
            state = AuthenticationUiState(
                email = "email@email.com",
                password = "password"
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AuthenticationRegistrationScreenPreview() {
    GreenMessangerTheme {
        AuthenticationContent(
            state = AuthenticationUiState(
                email = "email@email.com",
                password = "password",
                state = RegistrationState.REGISTRATION
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
private fun AuthenticationRegistrationScreenDarkPreview() {
    GreenMessangerTheme {
        AuthenticationContent(
            state = AuthenticationUiState(
                email = "email@email.com",
                password = "password",
                state = RegistrationState.REGISTRATION
            ),
            onAction = {}
        )
    }
}