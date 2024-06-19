package com.example.footballplayers.domain.repository

import androidx.lifecycle.LiveData
import com.example.footballplayers.domain.models.Player

interface PlayerRepository {

    fun getAllPlayers() : LiveData<List<Player>>

    suspend fun getPlayerById(id : Long) : Player

    suspend fun addPlayer(player: Player)

    suspend fun deletePlayer(id : Long)

    suspend fun updatePlayer(id: Long, newFirstName : String, newLastName : String)
}