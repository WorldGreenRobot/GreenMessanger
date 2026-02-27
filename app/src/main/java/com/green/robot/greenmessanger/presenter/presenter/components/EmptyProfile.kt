package com.green.robot.greenmessanger.presenter.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.green.robot.greenmessanger.R

@Composable
fun EmptyProfile(
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = ImageVector.vectorResource(R.drawable.ic_person_24dp),
        contentDescription = stringResource(R.string.profile_picture_empty),
        modifier = modifier
    )
}