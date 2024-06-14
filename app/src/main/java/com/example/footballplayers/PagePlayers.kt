package com.example.footballplayers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun Players(navController: NavController, vm: MyViewModel = viewModel()) {
    Scaffold (
        topBar = {
            AddTopBarToPage("PLAYERS")
        },
        floatingActionButton = {
            Box(modifier = Modifier.padding(end = 20.dp, bottom = 25.dp)) {
                FloatingActionButton(
                    onClick = {
                        vm.stateFirstName = ""
                        vm.stateLastName = ""
                        navController.navigate(Routes.NewPlayer.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            restoreState = true
                        }
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
            ContentForPlayers(navController, vm)
        }
    }
}

@Composable
fun ContentForPlayers(navController: NavController, vm: MyViewModel) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            AddItemOfList(navController = navController,"Daniil", "Huzarevich", vm)
            AddItemOfList(navController = navController,"Oleg", "Boreysha", vm)
        }

    }
}

@Composable
fun AddItemOfList(navController: NavController, firstName : String, lastName : String, vm: MyViewModel) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.padding(start = 20.dp, top = 5.dp)) {
                    Text(text = "$firstName $lastName", fontSize = 32.sp)
                }


                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp)
                    .clip(CircleShape),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .clickable {
                                handleEditButton(
                                    firstName,
                                    lastName,
                                    navController,
                                    vm
                                )
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
                            .clickable {
                                handleEditButton(
                                    firstName,
                                    lastName,
                                    navController,
                                    vm
                                )
                            },
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


fun handleEditButton(firstName: String, lastName: String, navController: NavController, vm: MyViewModel) {
    vm.stateFirstName = firstName
    vm.stateLastName = lastName
    navController.navigate(Routes.EditPlayer.route)
}