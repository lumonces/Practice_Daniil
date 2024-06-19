package com.example.footballplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballplayers.R
import com.example.footballplayers.domain.models.Player

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

@Composable
fun Content(vm : MainViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.PlayersPage.route) {
        composable(Routes.PlayersPage.route) { PlayersPage(navController, vm) }
        composable(Routes.NewPlayerPage.route) { NewPlayerPage(navController, vm) }
        composable(Routes.EditPlayerPage.route) { EditPlayerPage(navController, vm) }
    }
}

sealed class Routes(val route : String) {
    data object PlayersPage : Routes("PlayersPage")
    data object NewPlayerPage : Routes("NewPlayerPage")
    data object EditPlayerPage : Routes("EditPlayerPage")
}

// PlayersPage
@Composable
fun PlayersPage(navController: NavController, vm : MainViewModel) {
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
                        navController.navigate(Routes.NewPlayerPage.route) {
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
fun ContentForPlayers(navController: NavController, vm: MainViewModel) {
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
                    navController = navController,
                    vm = vm,
                    player = player
                )
            }
        }
    }
}

@Composable
fun AddItemOfList(navController: NavController, vm: MainViewModel, player: Player) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.padding(start = 20.dp, top = 5.dp)) {
                    Text(text = "${player.getFirstName()} ${player.getLastName()}", fontSize = 32.sp)
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
                                vm.editId(player.getId())
                                navController.navigate(Routes.EditPlayerPage.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
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
                            .clickable { vm.deletePlayer(player.getId()) },
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


// NewPlayerPage
@Composable
fun NewPlayerPage(navController: NavController, vm: MainViewModel) {
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
            ContentForNewPlayer(navController = navController, vm = vm)
        }
    }
}

@Composable
fun ContentForNewPlayer(navController: NavController, vm: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = vm.getFirstName(),
            onValueChange = { vm.editFirstName(it)},
            placeholder = { Text(text = "Firstname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        TextField(
            value = vm.getLastName(),
            onValueChange = { vm.editLastName(it) },
            placeholder = { Text(text = "Lastname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        Button(
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .height(60.dp),
            onClick = {
                vm.addPlayer()
                navController.navigate(Routes.PlayersPage.route) {
                    popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                    restoreState = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4B55B6),
                contentColor = Color.White
            ),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Save", fontSize = 24.sp)
            }
        }
    }
}


// EditPlayerPage
@Composable
fun EditPlayerPage(navController: NavController, vm : MainViewModel) {
    val allPlayers by vm.playerList.observeAsState(listOf())
    val player = allPlayers.first{ it.getId() == vm.getId() }
    vm.editFirstName(player.getFirstName())
    vm.editLastName(player.getLastName())
    Scaffold(
        topBar = {
            AddTopBarToPage("${player.getFirstName().uppercase()} ${player.getLastName().uppercase()}")
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
        ) {
            ContentForSpecificPlayer(navController = navController, vm = vm, player = player)
        }
    }
}

@Composable
fun ContentForSpecificPlayer(navController: NavController, vm : MainViewModel, player: Player) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = vm.getFirstName(),
            onValueChange = { vm.editFirstName(it) },
            placeholder = { Text(text = "Firstname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        TextField(
            value = vm.getLastName(),
            onValueChange = { vm.editLastName(it) },
            placeholder = { Text(text = "Lastname", fontSize = 23.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        Button(
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .height(60.dp),
            onClick = {
                vm.updatePlayer(player.getId())
                navController.navigate(Routes.PlayersPage.route) {
                    popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                    restoreState = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4B55B6),
                contentColor = Color.White
            ),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Save", fontSize = 24.sp)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopBarToPage(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .background(Color(0xFF4B55B6))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = title, fontSize = 28.sp)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4B55B6),
            titleContentColor = Color.White
        )
    )
}