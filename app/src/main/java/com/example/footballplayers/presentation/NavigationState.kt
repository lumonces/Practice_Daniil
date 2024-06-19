package com.example.footballplayers.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get

class NavigationState(private val navHostController: NavHostController) {
    fun navigateTo(route : String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph[Routes.PlayersPage.route].id) {
                saveState = true
            }
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
) : NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}