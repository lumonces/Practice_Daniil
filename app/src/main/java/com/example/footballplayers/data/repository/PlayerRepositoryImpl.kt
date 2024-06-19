package com.example.footballplayers.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.footballplayers.data.PlayerDAO
import com.example.footballplayers.data.mappers.toPlayer
import com.example.footballplayers.data.mappers.toPlayerEntity
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.repository.PlayerRepository

class PlayerRepositoryImpl(private val playerDAO: PlayerDAO) : PlayerRepository {

    override fun getAllPlayers(): LiveData<List<Player>> {
        return playerDAO.getAllPlayers().map {
            it.toPlayer()
        }
    }

    override suspend fun addPlayer(player: Player) {
        playerDAO.addPlayer(player.toPlayerEntity())
    }

    override suspend fun deletePlayer(id : Int) {
        playerDAO.deletePlayer(id)
    }

    override suspend fun updatePlayer(id: Int, newFirstName : String, newLastName : String) {
        playerDAO.updatePlayer(id, newFirstName, newLastName)
    }
}