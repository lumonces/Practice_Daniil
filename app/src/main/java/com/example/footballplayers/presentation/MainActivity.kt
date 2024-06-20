package com.example.footballplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.footballplayers.presentation.editPlayerPage.*
import com.example.footballplayers.presentation.navigation.Routes
import com.example.footballplayers.presentation.navigation.rememberNavigationState
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
    val navigationState = rememberNavigationState()
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Routes.ROUTE_PLAYERS_PAGE
    ) {
        composable(Routes.ROUTE_PLAYERS_PAGE) {
            PlayersPage(
                navigateToNewPlayerPage = {
                    navigationState.navigateTo(Routes.ROUTE_NEW_PLAYER_PAGE)
                },
                navigateToEditPage = {
                    navigationState.navigateTo(Routes.ROUTE_EDIT_PLAYER_PAGE)
                },
                vm = vm
            )
        }
        composable(Routes.ROUTE_NEW_PLAYER_PAGE) {
            NewPlayerPage(
                onBackPressed = { navigationState.onBackPressed() },
                vm = vm
            )
        }
        composable(Routes.ROUTE_EDIT_PLAYER_PAGE) {
            EditPlayerPage(
                onBackPressed = { navigationState.onBackPressed() },
                vm = vm
            )
        }
    }
}