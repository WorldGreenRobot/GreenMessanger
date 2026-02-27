package com.green.robot.greenmessanger.presenter.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.navigation3.runtime.NavKey
import com.green.robot.greenmessanger.presenter.domain.entity.user.User
import com.green.robot.greenmessanger.presenter.presenter.navigation.bottomMenuItems
import com.green.robot.greenmessanger.presenter.presenter.navigation.mainMenuItems

@Composable
fun NavigationDrawerContent(
    profile: User?,
    modifier: Modifier = Modifier,
    onNavigate: (NavKey) -> Unit = {}
) {
    HeaderDrawer(
        user = profile,
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.primaryContainer
            )
            .padding(top = 40.dp)
    )

    mainMenuItems.fastForEach { item ->
        NavigationDrawerItem(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(item.text),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            icon = {
                Icon(
                    painter = painterResource(item.icon),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(item.text)
                )
            },
            selected = false,
            onClick = {
                onNavigate(item.id)
            }
        )
    }

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onPrimary
    )

    bottomMenuItems.fastForEach { item ->
        NavigationDrawerItem(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(item.text),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            icon = {
                Icon(
                    painter = painterResource(item.icon),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(item.text)
                )
            },
            selected = false,
            onClick = {
                onNavigate(item.id)
            }
        )
    }
}

