package com.lyadsky.centr_invest_card_client.ui.root

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.components.root.RootComponent
import com.lyadsky.centr_invest_card_client.ui.views.snackbar.DefaultSnackbar
import com.lyadsky.centr_invest_card_client.utils.ErrorService
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get
import ui.bottomNavigation.BottomNavigationScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
    errorService: ErrorService = get()
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        snackbarHost = {
            DefaultSnackbar(snackbarHostState = it)
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            RootChildren(component = component, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}