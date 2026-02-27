package com.green.robot.greenmessanger.presenter.presenter.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.green.robot.greenmessanger.R

val mainMenuItems = listOf(
    DrawerItem(id = CreateGroup, text = R.string.create_group, R.drawable.ic_contacts_24dp),
    DrawerItem(id = CreateSecretChat, text = R.string.create_secret_chat, R.drawable.ic_encrypted_add_24dp),
    DrawerItem(id = Contacts, text = R.string.contacts, R.drawable.ic_contacts_24dp),
    DrawerItem(id = Calls, text = R.string.calls, R.drawable.ic_phone_enabled_24dp),
    DrawerItem(id = Settings, text = R.string.settings, R.drawable.ic_settings_24dp)
)

val bottomMenuItems = listOf(
    DrawerItem(id = Helps, text = R.string.invate_frinds, R.drawable.ic_person_add_24dp),
    DrawerItem(id = QuiestionAboutTelegram, text = R.string.quiestion_about_telegram, R.drawable.ic_help_24dp)
)

class DrawerItem(
    val id: NavKey,
    @param:StringRes val text: Int,
    @param:DrawableRes val icon: Int
)