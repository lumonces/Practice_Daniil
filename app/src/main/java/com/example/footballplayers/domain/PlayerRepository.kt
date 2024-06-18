package com.example.footballplayers.domain

import androidx.lifecycle.LiveData
import com.example.footballplayers.data.PlayerDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerRepository(private val playerDAO: PlayerDAO) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allPlayers : LiveData<List<Player>> = playerDAO.getAllPlayers()

    fun addPlayer(player: Player) {
        coroutineScope.launch(Dispatchers.IO) {
            playerDAO.addPlayer(player)
        }
    }

    fun deletePlayer(id : Int) {
        coroutineScope.launch(Dispatchers.IO) {
            playerDAO.deletePlayer(id)
        }
    }

    fun updatePlayer(id: Int, newFirstName : String, newLastName : String) {
        coroutineScope.launch(Dispatchers.IO) {
            playerDAO.updatePlayer(id, newFirstName, newLastName)
        }
    }
}