package com.example.footballplayers.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.footballplayers.data.PlayerRoom
import com.example.footballplayers.data.repository.PlayerRepositoryImpl
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.usecases.DeletePlayerUseCase
import com.example.footballplayers.domain.usecases.EditPlayerUseCase
import com.example.footballplayers.domain.usecases.NewPlayerUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val playerList : LiveData<List<Player>>
    private val playerRep : PlayerRepositoryImpl

    private var stateId by mutableIntStateOf(0)
    private var stateFirstName by mutableStateOf("")
    private var stateLastName by mutableStateOf("")

    init {
        val playerDao = PlayerRoom.getDataBase(application).playerDao()
        playerRep = PlayerRepositoryImpl(playerDao)
        playerList = playerRep.getAllPlayers()
    }

    private val newPlayerUseCase = NewPlayerUseCase(playerRep)
    private val editPlayerUseCase = EditPlayerUseCase(playerRep)
    private val deletePlayerUseCase = DeletePlayerUseCase(playerRep)

    fun addPlayer() {
        viewModelScope.launch {
            newPlayerUseCase.execute(Player(stateFirstName, stateLastName))
        }
    }

    fun deletePlayer(id : Int) {
        viewModelScope.launch {
            deletePlayerUseCase.execute(id)
        }
    }

    fun updatePlayer(id : Int) {
        viewModelScope.launch {
            editPlayerUseCase.execute(id, stateFirstName, stateLastName)
        }
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