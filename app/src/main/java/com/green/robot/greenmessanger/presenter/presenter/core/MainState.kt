package com.green.robot.greenmessanger.presenter.presenter.core

import androidx.navigation3.runtime.NavKey
import com.green.robot.greenmessanger.presenter.presenter.navigation.Authentication

data class MainState(
    val isLoadingApp: Boolean = true,
    val rootScreenRoute: NavKey = Authentication
)