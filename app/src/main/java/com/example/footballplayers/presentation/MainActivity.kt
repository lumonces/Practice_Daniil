package com.example.footballplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballplayers.presentation.editPlayerPage.*
import com.example.footballplayers.presentation.newPlayerPage.*
import com.example.footballplayers.presentation.playersPage.*

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
    val navigationState = rememberNavigationState(navController)
    NavHost(navController = navController, startDestination = Routes.ROUTE_PLAYERS_PAGE) {
        composable(Routes.PlayersPage.route) { PlayersPage(navigationState, vm) }
        composable(Routes.NewPlayerPage.route) { NewPlayerPage(navigationState, vm) }
        composable(Routes.EditPlayerPage.route) { EditPlayerPage(navigationState, vm) }
    }
}