package com.example.footballplayers.presentation.playersPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayers.R
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.presentation.MainViewModel
import com.example.footballplayers.presentation.NavigationState
import com.example.footballplayers.presentation.Routes

@Composable
fun AddItemOfList(navigationState: NavigationState, vm: MainViewModel, player: Player) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.padding(start = 20.dp, top = 5.dp)) {
                    Text(text = "${player.firstName} ${player.lastName}", fontSize = 32.sp)
                }

                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp)
                    .clip(CircleShape),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // КАРАНДАШ
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .clickable {
                                vm.editId(player.id)
                                navigationState.navigateTo(Routes.ROUTE_EDIT_PLAYER_PAGE)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.pensil),
                            contentDescription = "edit",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .clickable { vm.deletePlayer(player.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.cross),
                            contentDescription = "edit",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

        // Разделительная линия между элементами списка
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color(0xFFBEB8B8)))
    }
}