package com.example.footballplayers.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.footballplayers.data.PlayerRoom
import com.example.footballplayers.data.repository.PlayerRepositoryImpl
import com.example.footballplayers.domain.models.Player
import com.example.footballplayers.domain.usecases.CheckAuthorizationUseCase
import com.example.footballplayers.domain.usecases.DeletePlayerUseCase
import com.example.footballplayers.domain.usecases.EditPlayerUseCase
import com.example.footballplayers.domain.usecases.GetPlayerByIdUseCase
import com.example.footballplayers.domain.usecases.NewPlayerUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val playerList : LiveData<List<Player>>
    private val playerRep : PlayerRepositoryImpl

    init {
        val playerDao = PlayerRoom.getDataBase(application).playerDao()
        playerRep = PlayerRepositoryImpl(playerDao, context = application.applicationContext)
        playerList = playerRep.getAllPlayers()
    }

    private var stateId by mutableLongStateOf(0)
    private var stateFirstName by mutableStateOf("")
    private var stateLastName by mutableStateOf("")

    private var stateLogin by mutableStateOf(playerRep.getLogin())
    private var statePassword by mutableStateOf(playerRep.getPassword())



    private val newPlayerUseCase = NewPlayerUseCase(playerRep)
    private val editPlayerUseCase = EditPlayerUseCase(playerRep)
    private val deletePlayerUseCase = DeletePlayerUseCase(playerRep)
    private val getPlayerByIdUseCase = GetPlayerByIdUseCase(playerRep)
    private val checkAuthorizationUseCase = CheckAuthorizationUseCase(playerRep)

    fun checkAuthorization(login : String, password : String, navigateToPlayersPage : () -> Unit) {
        if (checkAuthorizationUseCase.execute(login = login, password = password)) {
            navigateToPlayersPage()
        }
    }

    fun getPlayerById(id : Long) : Player {
        return runBlocking {
            getPlayerByIdUseCase.execute(id)
        }
    }
    fun addPlayer() {
        viewModelScope.launch {
            newPlayerUseCase.execute(Player(stateFirstName, stateLastName))
        }
    }

    fun deletePlayer(id : Long) {
        viewModelScope.launch {
            deletePlayerUseCase.execute(id)
        }
    }

    fun updatePlayer(id : Long) {
        viewModelScope.launch {
            editPlayerUseCase.execute(id, stateFirstName, stateLastName)
        }
    }

    fun setId(newId : Long) {
        stateId = newId
    }

    fun setFirstName(newFirstName: String) {
        stateFirstName = newFirstName
    }

    fun setLastName(newLastName: String) {
        stateLastName = newLastName
    }

    fun setLogin(newLogin : String) {
        stateLogin = newLogin
    }

    fun setPassword(newPassword : String) {
        statePassword = newPassword
    }

    fun getId() : Long {
        return stateId
    }

    fun getFirstName() : String {
        return stateFirstName
    }

    fun getLastName() : String {
        return stateLastName
    }

    fun getLogin() : String {
        return stateLogin
    }

    fun getPassword() : String {
        return statePassword
    }
}