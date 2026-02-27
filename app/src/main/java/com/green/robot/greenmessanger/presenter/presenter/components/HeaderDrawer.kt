package com.green.robot.greenmessanger.presenter.presenter.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.green.robot.greenmessanger.presenter.domain.entity.user.User
import com.green.robot.greenmessanger.presenter.presenter.theme.GreenMessangerTheme

@Composable
fun HeaderDrawer(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = user.image,
            contentDescription = null,
            loading = {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.onSurface))
            },
            error = {
                EmptyProfile()
            },
            modifier = Modifier
        )

        Text(
            text = user.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun HeaderDrawerPreview() {
    GreenMessangerTheme {
        HeaderDrawer(
            user = User(
                id = "",
                name = "Пользователь",
                email = "your@mail.com",
                image = Uri.EMPTY
            )
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
private fun HeaderDrawerDarkPreview() {
    GreenMessangerTheme {
        HeaderDrawer(
            user = User(
                id = "",
                name = "Пользователь",
                email = "your@mail.com",
                image = Uri.EMPTY
            )
        )
    }
}