package com.example.footballplayers.domain.usecases

import com.example.footballplayers.domain.repository.PlayerRepository

class DeletePlayerUseCase(private val playerRepository: PlayerRepository) {
    suspend fun execute(id : Int) {
        playerRepository.deletePlayer(id)
    }
}