package com.example.footballplayers.domain.usecases

import androidx.lifecycle.LiveData
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.repository.PlayerRepository

class GetPlayerByIdUseCase(private val playerRepository: PlayerRepository) {

    suspend fun execute(id : Long) : Player {
        return playerRepository.getPlayerById(id)
    }
}