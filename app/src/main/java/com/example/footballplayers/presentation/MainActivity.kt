package com.example.footballplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.footballplayers.presentation.authorization.AuthorizationPage
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
        startDestination = Routes.AuthorizationPage.route
    ) {
        composable(Routes.PlayersPage.route) {
            PlayersPage(
                navigateToNewPlayerPage = {
                    navigationState.navigateTo(Routes.NewPlayerPage.route)
                },
                navigateToEditPage = {
                    navigationState.navigateTo(Routes.EditPlayerPage.route)
                },
                vm = vm
            )
        }
        composable(Routes.NewPlayerPage.route) {
            NewPlayerPage(
                onBackPressed = { navigationState.onBackPressed() },
                vm = vm
            )
        }
        composable(Routes.EditPlayerPage.route) {
            EditPlayerPage(
                onBackPressed = { navigationState.onBackPressed() },
                vm = vm
            )
        }
        composable(Routes.AuthorizationPage.route) {
            AuthorizationPage(
                navigateToPlayersPage = { navigationState.navigateTo(Routes.PlayersPage.route) },
                vm = vm
            )
        }
    }
}