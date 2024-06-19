package com.example.footballplayers.domain.usecases

import com.example.footballplayers.domain.repository.PlayerRepository

class EditPlayerUseCase(private val playerRepository: PlayerRepository) {

    suspend fun execute(id : Int, newFirstName : String, newLastName : String) {
        if(newFirstName != "" && newLastName != "") {
            playerRepository.updatePlayer(id, newFirstName, newLastName)
        }
    }
}