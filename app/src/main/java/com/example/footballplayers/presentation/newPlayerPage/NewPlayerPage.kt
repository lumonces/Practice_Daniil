package com.example.footballplayers.presentation.newPlayerPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.footballplayers.presentation.AddTopBarToPage
import com.example.footballplayers.presentation.MainViewModel
import com.example.footballplayers.presentation.NavigationState

@Composable
fun NewPlayerPage(navigationState: NavigationState, vm: MainViewModel) {
    Scaffold(
        topBar = {
            AddTopBarToPage("NEW PLAYER")
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
        ) {
            ContentForNewPlayer(navigationState = navigationState, vm = vm)
        }
    }
}