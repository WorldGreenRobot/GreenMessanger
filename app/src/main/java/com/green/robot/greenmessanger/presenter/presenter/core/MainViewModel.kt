package com.green.robot.greenmessanger.presenter.presenter.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.green.robot.greenmessanger.presenter.presenter.navigation.Chats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _navigationState = MutableStateFlow(MainState())
    val navigationState = _navigationState.asStateFlow()
}