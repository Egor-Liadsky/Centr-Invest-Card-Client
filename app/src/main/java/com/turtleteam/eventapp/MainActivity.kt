package com.turtleteam.eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.Settings
import com.turtleteam.core_view.theme.EventAppTheme
import com.turtleteam.core_view.view.textfields.AssistantTextField
import com.turtleteam.eventapp.navigation.MainNavigationScreen
import com.turtleteam.impl.SpeakerService
import com.turtleteam.impl.navigation.AccountNavigator
import com.turtleteam.impl.presentation.pincode.PincodeScreen
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val speakerService by inject<SpeakerService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        speakerService.init(this)
        setContent {
            val navController = rememberNavController()
            val settings = koinInject<Settings>()

            val isDark = settings.theme.collectAsState(initial = false)
            EventAppTheme(isDark.value) {
                MainNavigationScreen(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        speakerService.destroy()
        super.onDestroy()
    }
}
