package com.example.footballplayers.domain.repository

import androidx.lifecycle.LiveData
import com.example.footballplayers.domain.models.Player

interface PlayerRepository {

    fun getAllPlayers() : LiveData<List<Player>>

    suspend fun addPlayer(player: Player)

    suspend fun deletePlayer(id : Int)

    suspend fun updatePlayer(id: Int, newFirstName : String, newLastName : String)
}