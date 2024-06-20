package com.example.footballplayers.presentation.navigation

sealed class Routes(val route : String) {
    data object AuthorizationPage : Routes(route = ROUTE_AUTHORIZATION_PAGE)
    data object PlayersPage : Routes(route = ROUTE_PLAYERS_PAGE)
    data object NewPlayerPage : Routes(route = ROUTE_NEW_PLAYER_PAGE)
    data object EditPlayerPage : Routes(route = ROUTE_EDIT_PLAYER_PAGE)

    companion object {
        const val ROUTE_AUTHORIZATION_PAGE = "AuthorizationPage"
        const val ROUTE_PLAYERS_PAGE = "PlayersPage"
        const val ROUTE_NEW_PLAYER_PAGE = "NewPlayerPage"
        const val ROUTE_EDIT_PLAYER_PAGE = "EditPlayerPage"
    }
}