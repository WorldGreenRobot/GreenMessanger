package com.green.robot.greenmessanger.presenter.presenter.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.green.robot.greenmessanger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDrawer(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    drawerContent: @Composable ColumnScope.() -> Unit,
    uiError: String? = null,
    isRefreshing: Boolean = false,
    onRefresh: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    content: @Composable () -> Unit,
) {
    val pullToRefreshState: PullToRefreshState = rememberPullToRefreshState()

    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(DrawerValue.Open)

    NavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                drawerContent()
            }
        }
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .then(
                    if (scrollBehavior != null) {
                        Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                    } else Modifier
                ),
            topBar = topBar,
            bottomBar = bottomBar,
            floatingActionButton = floatingActionButton,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = { innerPadding ->
                PullToRefreshBox(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    state = pullToRefreshState,
                    onRefresh = {
                        onRefresh?.invoke()
                    },
                    isRefreshing = isRefreshing,
                    indicator = {
                        Indicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            isRefreshing = isRefreshing,
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            state = pullToRefreshState
                        )
                    }
                ) {
                    content()
                }
            }
        )
    }
    val snackbarAction = stringResource(R.string.repeat)
    val errorMessage =
        if (!uiError.isNullOrBlank()) stringResource(R.string.unknown_error) else null
    LaunchedEffect(uiError, errorMessage) {
        if (uiError != null && errorMessage != null) {
            snackbarHostState.showSnackbar(
                errorMessage,
                actionLabel = snackbarAction,
                duration = SnackbarDuration.Indefinite
            ).let {
                if (it == SnackbarResult.ActionPerformed) {
                    onRefresh?.invoke()
                }
            }
        } else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

}
