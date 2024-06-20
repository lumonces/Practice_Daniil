package com.example.footballplayers.domain.usecases

import com.example.footballplayers.domain.repository.PlayerRepository


class CheckAuthorizationUseCase(private val playerRepository: PlayerRepository) {

    fun execute(login : String, password : String) : Boolean {
        if(playerRepository.getLogin() != "" && playerRepository.getPassword() != "") {
            return playerRepository.getLogin() == login && playerRepository.getPassword() == password
        }
        else {
            playerRepository.saveLoginPassword(login = login, password = password)
            return true
        }
    }
}