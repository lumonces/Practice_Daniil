package com.example.footballplayers.domain.usecases

import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.repository.PlayerRepository

class NewPlayerUseCase(private val playerRepository: PlayerRepository) {
    suspend fun execute(player : Player) {
        if(player.firstName != "" && player.lastName != "") {
            playerRepository.addPlayer(player)
        }
    }
}