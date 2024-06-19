package com.example.footballplayers.presentation

sealed class Routes(val route : String) {
    data object PlayersPage : Routes(route = ROUTE_PLAYERS_PAGE)
    data object NewPlayerPage : Routes(route = ROUTE_NEW_PLAYER_PAGE)
    data object EditPlayerPage : Routes(route = ROUTE_EDIT_PLAYER_PAGE)

    companion object {
        const val ROUTE_PLAYERS_PAGE = "PlayersPage"
        const val ROUTE_NEW_PLAYER_PAGE = "NewPlayerPage"
        const val ROUTE_EDIT_PLAYER_PAGE = "EditPlayerPage"
    }
}