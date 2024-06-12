package com.example.workwithnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

sealed class Routes(val route: String) {
    data object Home : Routes("Home")
    data object Library : Routes("Library")
    data object Setting : Routes("Setting")
}

@Composable
fun Content() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Home.route) { AddTextOnPage("Home") }
            composable(Routes.Library.route) { AddTextOnPage("Library") }
            composable(Routes.Setting.route) { AddTextOnPage("Setting") }
        }
    }
}

@Composable
fun NavBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF4B55B6)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach{navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                          navController.navigate(navItem.route) {
                              popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                              launchSingleTop = true
                              restoreState = true
                          }
                },
                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title,
                        tint = Color.White)
                },
                label = {
                    Text(text = navItem.title, fontSize = 24.sp, color = Color.White)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

object NavBarItems{
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = Routes.Home.route
        ),

        BarItem(
            title = "Library",
            image = Icons.Filled.Email,
            route = Routes.Library.route
        ),

        BarItem(
            title = "Setting",
            image = Icons.Filled.Settings,
            route = Routes.Setting.route
        )
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

@Composable
fun AddTextOnPage(text: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text, fontSize = 48.sp)
    }
}
