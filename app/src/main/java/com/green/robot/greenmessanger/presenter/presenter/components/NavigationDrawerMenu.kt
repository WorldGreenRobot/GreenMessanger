package com.green.robot.greenmessanger.presenter.presenter.components

import androidx.annotation.DrawableRes
import com.green.robot.greenmessanger.R

val mainMenuItems = listOf(
    DrawerItem(id = "create_group", text = "Profile", R.drawable.ic_contacts_24dp),
    DrawerItem(id = "settings", text = "Settings"),
)

class DrawerItem(
    val id: String,
    val text: String,
    @param:DrawableRes val icon: Int
)