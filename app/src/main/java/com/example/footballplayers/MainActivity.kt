package com.example.footballplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballplayers.database.MyViewModel

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
fun Content(vm: MyViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Players.route) {
        composable(Routes.Players.route) { Players(navController, vm) }
        composable(Routes.NewPlayer.route) { NewPlayer(navController, vm) }
        composable(Routes.EditPlayer.route) { EditPlayer(navController, vm) }
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

sealed class Routes(val route: String) {
    data object Players : Routes("Players")
    data object NewPlayer : Routes("NewPlayer")
    data object EditPlayer : Routes("EditPlayer")
}