package com.example.footballplayers.presentation.playersPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayers.presentation.AddTopBarToPage
import com.example.footballplayers.presentation.MainViewModel

@Composable
fun PlayersPage(navigateToNewPlayerPage : () -> Unit, navigateToEditPage : () -> Unit, vm : MainViewModel) {
    Scaffold (
        topBar = {
            AddTopBarToPage("PLAYERS")
        },
        floatingActionButton = {
            Box(modifier = Modifier.padding(end = 20.dp, bottom = 25.dp)) {
                FloatingActionButton(
                    onClick = {
                        vm.editFirstName("")
                        vm.editLastName("")
                        navigateToNewPlayerPage()
                    },
                    shape = CircleShape,
                    containerColor = Color(0xFF4B55B6),
                    contentColor = Color.White
                ) {
                    Text(text = "+", fontSize = 24.sp)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0xFFD9D9D9))
        ) {
            ContentForPlayers(navigateToEditPage, vm)
        }
    }
}



