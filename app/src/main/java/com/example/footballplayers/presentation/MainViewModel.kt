package com.example.footballplayers.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.footballplayers.data.PlayerRoom
import com.example.footballplayers.domain.Player
import com.example.footballplayers.domain.PlayerRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val playerList : LiveData<List<Player>>
    private val playerRep : PlayerRepository

    private var stateId by mutableIntStateOf(0)
    private var stateFirstName by mutableStateOf("")
    private var stateLastName by mutableStateOf("")

    init {
        val playerDao = PlayerRoom.getDataBase(application).playerDao()
        playerRep = PlayerRepository(playerDao)
        playerList = playerRep.allPlayers
    }

    fun addPlayer() {
        playerRep.addPlayer(Player(stateFirstName, stateLastName))
    }

    fun deletePlayer(id : Int) {
        playerRep.deletePlayer(id)
    }

    fun updatePlayer(id : Int) {
        playerRep.updatePlayer(id, stateFirstName, stateLastName)
    }

    fun editId(newId : Int) {
        stateId = newId
    }

    fun editFirstName(newFirstName: String) {
        stateFirstName = newFirstName
    }

    fun editLastName(newLastName: String) {
        stateLastName = newLastName
    }

    fun getId() : Int {
        return stateId
    }

    fun getFirstName() : String {
        return stateFirstName
    }

    fun getLastName() : String {
        return stateLastName
    }
}