package com.example.footballplayers.presentation.playersPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.footballplayers.presentation.MainViewModel

@Composable
fun ContentForPlayers(navigateToEditPage : () -> Unit, vm: MainViewModel) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .fillMaxSize()
    ) {
        val playersList by vm.playerList.observeAsState(listOf())
        LazyColumn {
            items(playersList) { player->
                AddItemOfList(
                    navigateToEditPage = navigateToEditPage,
                    vm = vm,
                    player = player
                )
            }
        }
    }
}