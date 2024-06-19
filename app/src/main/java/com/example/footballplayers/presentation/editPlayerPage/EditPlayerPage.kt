package com.example.footballplayers.presentation.editPlayerPage

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
fun EditPlayerPage(navigationState: NavigationState, vm : MainViewModel) {
    val player = vm.getPlayerById(vm.getId())
    println("${player.id} ${player.firstName} ${player.lastName}")
    vm.editFirstName(player.firstName)
    vm.editLastName(player.lastName)
    Scaffold(
        topBar = {
            AddTopBarToPage("${player.firstName.uppercase()} ${player.lastName.uppercase()}")
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
        ) {
            ContentForEditablePlayer(navigationState = navigationState, vm = vm, player = player)
        }
    }
}

