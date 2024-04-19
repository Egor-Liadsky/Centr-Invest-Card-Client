package com.lyadsky.centr_invest_card_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.lyadsky.centr_invest_card_client.components.root.RootComponentImpl
import com.lyadsky.centr_invest_card_client.ui.root.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(componentContext = defaultComponentContext())

        enableEdgeToEdge()

        setContent {
            RootScreen(component = rootComponent)
        }
    }
}
