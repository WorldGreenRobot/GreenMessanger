package com.green.robot.greenmessanger.presenter.domain.entity.user

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val image: Uri,
)
