package com.example.footballplayers.presentation.authorization

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

@Composable
fun AuthorizationPage(navigateToPlayersPage : () -> Unit, vm : MainViewModel) {
    Scaffold (
        topBar = {
            AddTopBarToPage("Authorization")
        }
    ) {innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0xFFD9D9D9))
        ) {
            ContentForAuthorization(navigateToPlayersPage, vm)
        }
    }
}