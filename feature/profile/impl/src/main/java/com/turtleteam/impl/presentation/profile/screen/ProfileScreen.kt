package com.turtleteam.impl.presentation.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.turtleteam.impl.presentation.profile.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val state = viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text("Profile")
    }
}